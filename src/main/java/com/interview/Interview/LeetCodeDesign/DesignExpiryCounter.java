package com.interview.Interview.LeetCodeDesign;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/*

Operations:

put_element(k)

cleanup() expired nodes.
count[k]++, total++.
Push (expireAt = now + ttl) into heap.

get_element_count(k)

cleanup(); return count.getOrDefault(k, 0).

get_total_elements()

cleanup(); return total.

Cleanup:
While the heap’s top expireAt <= now: pop it, decrement that key’s count (and total). Remove key if its count hits zero.

 */
public class DesignExpiryCounter<K> {

    /** Time source for testability. */
    interface Ticker {
        long nowNanos();
    }

    /** Default ticker using System.nanoTime(). */
    static final class SystemTicker implements Ticker {
        @Override public long nowNanos() { return System.nanoTime(); }
    }

    /** One node per inserted event. */
    private static final class ExpiryNode<K> {
        final K key;
        final long expireAtNanos;
        ExpiryNode(K key, long expireAtNanos) {
            this.key = key;
            this.expireAtNanos = expireAtNanos;
        }
    }

    private final Map<K, Integer> counts = new HashMap<>();
    private final PriorityQueue<ExpiryNode<K>> minHeap;
    private final long ttlNanos;
    private final Ticker ticker;

    private long total = 0; // total live elements

    /** Construct with TTL in seconds (as per problem statement). */
    public DesignExpiryCounter(long ttlSeconds) {
        this(ttlSeconds, TimeUnit.SECONDS, new SystemTicker());
    }

    /** Construct with custom units and ticker (useful for unit tests). */
    public DesignExpiryCounter(long ttl, TimeUnit unit, Ticker ticker) {
        this.ttlNanos = unit.toNanos(ttl);
        this.ticker = ticker;
        this.minHeap = new PriorityQueue<>(Comparator.comparingLong(n -> n.expireAtNanos));
    }

    /** O(log N) push + amortized O(1) cleanup cost. */
    public void putElement(K key) {
        cleanup();
        counts.merge(key, 1, Integer::sum);
        total++;
        long expireAt = ticker.nowNanos() + ttlNanos;
        minHeap.offer(new ExpiryNode<>(key, expireAt));
    }

    /** Returns current live count for key. */
    public int getElementCount(K key) {
        cleanup();
        return counts.getOrDefault(key, 0);
    }

    /** Returns total live elements across all keys. */
    public long getTotalElements() {
        cleanup();
        return total;
    }

    /** Drop all expired events; amortized O(1) per event overall. */
    private void cleanup() {
        long now = ticker.nowNanos();
        while (!minHeap.isEmpty() && minHeap.peek().expireAtNanos <= now) {
            ExpiryNode<K> node = minHeap.poll();
            Integer c = counts.get(node.key);
            if (c != null && c > 0) {
                if (c == 1) counts.remove(node.key);
                else counts.put(node.key, c - 1);
                total--;
            }
            // No need for tombstones; each put has exactly one expiry.
        }
    }
}
