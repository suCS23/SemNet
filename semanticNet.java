package code_garage.academic.assignments.cpcs331.SemNet;

import java.util.*;

class semanticNet {
    Map<String, node> nodes = new HashMap<>();

    node getOrCreateNode(String name) {
        return nodes.computeIfAbsent(name, node::new);
    }

    void addRelation(String source, String relation, String target) {
        node s = getOrCreateNode(source);
        node t = getOrCreateNode(target);
        s.edges.add(new edge(relation, t));
    }

    void searchRelation(String source, String relation, String target) {
        node s = nodes.get(source);
        if (s == null) {
            System.out.println("Source node '" + source + "' not found.");
            return;
        }

        boolean found = false;

        if (relation != null && target != null) {
            for (edge e : s.edges) {
                if (e.relation.equalsIgnoreCase(relation) && e.target.name.equalsIgnoreCase(target)) {
                    System.out.println("Found relation: " + source + " " + relation + " " + target);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Relation not found.");
        }

        else {
            if (s.edges.isEmpty()) {
                System.out.println("Node '" + source + "' has no relations.");
                return;
            }
            System.out.println("Relations for '" + source + "':");
            for (edge e : s.edges) {
                System.out.println("  --" + e.relation + "--> " + e.target.name);
            }
        }
    }

    void delRelation(String source, String relation, String target) {
        node s = nodes.get(source);
        if (s == null) {
            System.out.println("Source node '" + source + "' not found.");
            return;
        }

        boolean removed = s.edges.removeIf(e ->
            e.relation.equals(relation) && e.target.name.equals(target)
        );

        if (removed)
            System.out.println("Relation '" + source + " " + relation + " " + target + "' removed.");
        else
            System.out.println("Relation not found.");
    }

    void printNetwork() {
        for (node n : nodes.values()) {
            for (edge e : n.edges) {
                System.out.println(n.name + " " + e.relation + " " + e.target.name);
            }
        }
    }
}

class node {
    String name;
    LinkedList<edge> edges;

    node(String name) {
        this.name = name;
        this.edges = new LinkedList<>();
    }
}

class edge {
    String relation;
    node target;

    edge(String relation, node target) {
        this.relation = relation;
        this.target = target;
    }
}
