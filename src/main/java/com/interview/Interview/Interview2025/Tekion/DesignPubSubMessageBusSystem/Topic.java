package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.Partition;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    String topicId;
    private String name;
    private List<Partition> partitions; // Corrected spelling

    public Topic(String name, int partitionCount) {
        this.name = name;
        this.partitions = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {

            Partition partition = new Partition();
            partition.setpId(i);
            partitions.add(partition);
        }
    }

    public String getName() {
        return name;
    }

    public List<Partition> getPartitions() { // Corrected spelling
        return partitions;
    }

    public int getPartitionCount() {
        return partitions.size();
    }

    public Partition getPartition(int index) {
        if (index < 0 || index >= partitions.size()) {
            throw new IllegalArgumentException("Invalid partition index");
        }
        return partitions.get(index);
    }
}



