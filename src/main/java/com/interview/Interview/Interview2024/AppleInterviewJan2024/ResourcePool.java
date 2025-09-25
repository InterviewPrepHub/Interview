package com.interview.Interview.Interview2024.AppleInterviewJan2024;

import java.util.ArrayList;
import java.util.List;

/*
To create a resource pool in Java, you can start with the basic structure provided.
I'll add some fields, methods, and a basic structure to manage the resources.
This example assumes that Resource is a class representing the resources you want to manage.
 */

class Resource {

}

public class ResourcePool {

    private List<Resource> resources;

    /**
     * Construct a ResourcePool containing the specified
     * number of Resources.
     *
     * @param size the number of Resources to be contained by the pool.
     */
    public ResourcePool(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Pool size must be greater than zero");
        }

        resources = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            resources.add(new Resource()); // Assuming Resource is a class representing the resource
        }
    }

    /**
     * Acquire a resource from the pool.
     *
     * @return a Resource object from the pool, or null if none are available.
     */
    public synchronized Resource acquireResource() {
        if (!resources.isEmpty()) {
            return resources.remove(0);
        } else {
            return null; // No resources available
        }
    }

    /**
     * Release a resource back to the pool.
     *
     * @param resource the Resource object to be released.
     */
    public synchronized void releaseResource(Resource resource) {
        resources.add(resource);
    }

    /**
     * Get the current size of the resource pool.
     *
     * @return the current size of the pool.
     */
    public int getSize() {
        return resources.size();
    }
}

/*
In the claimResource method, I've added synchronization to ensure that multiple threads won't access the pool
simultaneously, preventing race conditions. The method checks if there are available resources in the pool,
removes one if available, and returns it. If no resources are available, it returns null.

The releaseResource method is also synchronized to avoid potential issues with multiple threads trying to release
resources simultaneously. It simply adds the released resource back to the pool.

 */
