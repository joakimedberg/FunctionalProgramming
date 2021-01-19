package nackademin.assignment2;

import java.util.*;


public class assignment2 {
    HashMap<String, List<String>> map;
    List<String> minors;
    List<String> superiors;

    public static void main(String[] args) {
        new assignment2().init();

    }

    void userInterface() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Search minors");
        getMinors(sc.next()).forEach(System.out::println);
        System.out.println();
        System.out.println("Search superiors");
        getSuperiors(sc.next()).forEach(System.out::println);


    }

    void init() {
        map = new HashMap<>();
        minors = new ArrayList<>();
        superiors = new ArrayList<>();
        map.put("Tomten", Arrays.asList("Glader", "Butter"));
        // map.put("Glader", Arrays.asList("Tröger", "Trötter", "Blyger"));
        map.put("Butter", Arrays.asList("Rådjuret", "Nyckelpigan", "Haren", "Räven"));
        map.put("Trötter", Arrays.asList("Skumtomten"));
        map.put("Skumtomten", Arrays.asList("Dammråttan"));
        map.put("Glader", Arrays.asList("Tröger", "Trötter", "Blyger"));
        map.put("Räven", Arrays.asList("Gråsuggan", "Myran"));
        map.put("Myran", Arrays.asList("Bladlusen"));

        userInterface();
    }

    List<String> getSuperiors(String name) {
        superiors.add("*" + name + "*");
        if (map.values().stream().filter(e -> e.contains(name)).count() > 0) {
            return getSuperiors(Arrays.asList(name));
        } else {
            return Arrays.asList("Finns ej");
        }
    }

    List<String> getSuperiors(List<String> names) {
        String name = names.get(names.size() - 1);

        map.forEach((key, value) -> {
            if (value.contains(name)) {
                superiors.add(key);
                getSuperiors(superiors);
            }
        });

        return superiors;

    }

    List<String> getMinors(List<String> names) {
        List<String> list = new ArrayList<>(names);

        for (String n : names) {
            if (map.containsKey(n)) {
                map.get(n).forEach(minors::add);
                map.get(n).forEach(list::add);
            }
            list.remove(n);
            return getMinors(list);
        }
        return minors;
    }

    List<String> getMinors(String name) {
        if (map.containsKey(name)) {
            map.get(name).forEach(minors::add);
            return getMinors(minors);
        } else {
            return Arrays.asList("Finns ej");
        }

    }
}

