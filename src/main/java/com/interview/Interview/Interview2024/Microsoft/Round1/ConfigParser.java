package com.interview.Interview.Interview2024.Microsoft.Round1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
write a function, parse_config(config_file), which reads a configuration file and transforms its content into a dictionary. The configuration file has the following characteristics:

Each line represents a key-value pair separated by an equal sign (=).
Lines starting with a # are comments and should be ignored.
Empty lines should also be ignored.
The part before the = is the key, and the part after is the value.
Both keys and values should be trimmed of any leading or trailing whitespace.
The expected output is a dictionary where each key-value pair from the configuration file becomes a key-value pair in the dictionary.


Given a .txt file that emulates an environment file. Implement a function parse_config(config_file) where config_file is a string. The function takes a path to a config file as argument, transforms its content into dictionary and return the dictionary

example
env file below :
secret_token = 0.465456875867rew

and when converted to dictionary should look like this
{'secret_token' : '0.465456875867rew'}

in other words, whatever comes after the first '=' is value, which will always be a string meaning that nested value are not expected. Eaxh non empty line represents a new element int th dictionary

example:

role_id=2hwhguw-2bfkdb-3kjewhg
role_secret=fkehfueh-y5yyi-3823ri3
#this is a comment

ENVIRONMENT=Dev
S2_STP=example.net

expected output

{
     'role_id': '2hwhguw-2bfkdb-3kjewhg',
     'role_secret': 'fkehfueh-y5yyi-3823ri3'
      'ENVIRONMENT': 'Dev'
      'S2_STP': 'example.net'
}

 */
public class ConfigParser {

    public static Map<String, String> parseConfig(String configFile) throws IOException {
        Map<String, String> configMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            int indexOfEqual = line.indexOf('=');
            if (indexOfEqual != -1) {
                String key = line.substring(0, indexOfEqual).trim();
                String value = line.substring(indexOfEqual + 1).trim();
                configMap.put(key, value);
            }
        }

        reader.close();
        return configMap;
    }

    public static void main(String[] args) {
        try {
            String configFilePath = "path_to_your_config_file.txt";
            Map<String, String> config = parseConfig(configFilePath);
            System.out.println(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
