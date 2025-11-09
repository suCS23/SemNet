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

        for (edge e : s.edges) {
            if (e.relation.equalsIgnoreCase(relation)) {
                System.out.println("Warning: '" + source + "' already has a relation '" +
                        relation + "' with '" + e.target.name + "'.");
            }
        }

        s.edges.add(new edge(relation, t));
        System.out.println("Added: " + source + " " + relation + " " + target);
    }

    void delRelation(String source, String relation, String target) {
        node s = nodes.get(source);
        if (s == null) {
            System.out.println("Source node '" + source + "' not found.");
            return;
        }

        boolean removed = s.edges.removeIf(e ->
                e.relation.equalsIgnoreCase(relation) && e.target.name.equalsIgnoreCase(target)
        );

        if (removed)
            System.out.println("Relation '" + source + " " + relation + " " + target + "' removed.");
        else
            System.out.println("Relation not found.");
    }

    void delNode(String name) {
        node toRemove = nodes.remove(name);
        if (toRemove == null) {
            System.out.println("Node '" + name + "' not found.");
            return;
        }

        for (node n : nodes.values()) {
            n.edges.removeIf(e -> e.target.name.equalsIgnoreCase(name));
        }

        System.out.println("Node '" + name + "' and its relations were deleted.");
    }

    void printNetwork() {
        if (nodes.isEmpty()) {
            System.out.println("The semantic network is empty.");
            return;
        }

        System.out.println("=== Semantic Network ===");
        for (node n : nodes.values()) {
            for (edge e : n.edges) {
                System.out.println(n.name + " " + e.relation + " " + e.target.name);
            }
        }
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
                if (e.relation.equalsIgnoreCase(relation) &&
                    e.target.name.equalsIgnoreCase(target)) {
                    System.out.println("Found relation: " + source + " " + relation + " " + target);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Relation not found.");
        } else {
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

    void inferRelations() {
        List<String> inferred = new ArrayList<>();

        for (node n : nodes.values()) {
            for (edge e1 : n.edges) {
                if (e1.relation.equalsIgnoreCase("is a")) {
                    node mid = e1.target;
                    for (edge e2 : mid.edges) {
                        if (e2.relation.equalsIgnoreCase("is a")) {
                            if (!hasRelation(n.name, "is a", e2.target.name)) {
                                addRelation(n.name, "is a", e2.target.name);
                                inferred.add(n.name + " is a " + e2.target.name);
                            }
                        }
                    }
                }
            }
        }

        if (inferred.isEmpty()) {
            System.out.println("No new inferences found.");
        } else {
            System.out.println("Inferred relations:");
            for (String r : inferred) {
                System.out.println("  " + r);
            }
        }
    }

    boolean hasRelation(String source, String relation, String target) {
        node s = nodes.get(source);
        if (s == null) return false;
        for (edge e : s.edges) {
            if (e.relation.equalsIgnoreCase(relation) && e.target.name.equalsIgnoreCase(target))
                return true;
        }
        return false;
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
