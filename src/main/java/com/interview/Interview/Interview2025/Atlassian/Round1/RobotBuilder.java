package com.interview.Interview.Interview2025.Atlassian.Round1;

import java.util.*;

/*
of robot parts in a factory. Each part goes to a robot with a specific, unique name. Each part will be described by a string, with the name of the robot and the part name separated by an underscore, like "Rosie_arm".

All robots are made of the same types of parts, and we have a string of all of the parts required to form a complete robot. Given a list of available parts, return the collection of robot names for which we can build at least one complete robot

all_parts = [
    "Rosie_claw",
    "Rosie_sensors",
    "Dustie_case",
    "Optimus_sensors",
    "Rust_sensors",
    "Rosie_case",
    "Rust_case",
    "Optimus_speaker",
    "Rosie_wheels",
    "Rosie_speaker",
    "Dustie_case",
    "Dustie_arms",
    "Rust_claw",
    "Dustie_case",
    "Dustie_speaker",
    "Optimus_case",
    "Optimus_wheels",
    "Rust_legs",
    "Optimus_sensors" ]

required_parts_1 = "sensors,case,speaker,wheels"
required_parts_2 = "sensors,case,speaker,wheels,claw"
required_parts_3 = "sensors,case,screws"

get_robots(all_parts, required_parts_1) => ["Optimus", "Rosie"]
get_robots(all_parts, required_parts_2) => ["Rosie"]
get_robots(all_parts, required_parts_3) => []

N: Number of elements in all_parts
P: Number of elements in required_parts

coding

public static void main(String[] argv) {
    String required_parts_1 = "sensors,case,speaker,wheels";
    String required_parts_2 = "sensors,case,speaker,wheels,claw";
    String required_parts_3 = "sensors,case,screws";

    String[] all_parts = {
      "Rosie_claw",
      "Rosie_sensors",
      "Dustie_case",
      "Optimus_sensors",
      "Rust_sensors",
      "Rosie_case",
      "Rust_case",
      "Optimus_speaker",
      "Rosie_wheels",
      "Rosie_speaker",
      "Dustie_case",
      "Dustie_arms",
      "Rust_claw",
      "Dustie_case",
      "Dustie_speaker",
      "Optimus_case",
      "Optimus_wheels",
      "Rust_legs",
      "Optimus_sensors"};
  }
 */


import java.util.*;

public class RobotBuilder {

    public static List<String> getRobots(String[] allParts, String requiredPartsStr) {
        Set<String> requiredParts = new HashSet<>(Arrays.asList(requiredPartsStr.split(",")));

        // Map: robot name -> set of parts
        Map<String, Set<String>> robotToParts = new HashMap<>();

        for (String part : allParts) {
            String[] split = part.split("_");
            if (split.length != 2) continue; // skip malformed

            String robot = split[0];
            String partName = split[1];

            robotToParts.putIfAbsent(robot, new HashSet<>());
            robotToParts.get(robot).add(partName);
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : robotToParts.entrySet()) {
            if (entry.getValue().containsAll(requiredParts)) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result); // Optional: consistent order
        return result;
    }

    public static void main(String[] argv) {
        String required_parts_1 = "sensors,case,speaker,wheels";
        String required_parts_2 = "sensors,case,speaker,wheels,claw";
        String required_parts_3 = "sensors,case,screws";

        String[] all_parts = {
                "Rosie_claw", "Rosie_sensors", "Dustie_case", "Optimus_sensors", "Rust_sensors",
                "Rosie_case", "Rust_case", "Optimus_speaker", "Rosie_wheels", "Rosie_speaker",
                "Dustie_case", "Dustie_arms", "Rust_claw", "Dustie_case", "Dustie_speaker",
                "Optimus_case", "Optimus_wheels", "Rust_legs", "Optimus_sensors"
        };

        System.out.println(getRobots(all_parts, required_parts_1)); // [Optimus, Rosie]
        System.out.println(getRobots(all_parts, required_parts_2)); // [Rosie]
        System.out.println(getRobots(all_parts, required_parts_3)); // []
    }
}
