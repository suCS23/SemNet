package code_garage.academic.assignments.cpcs331.SemNet;

public class main {
    public static void main(String[] args) {
        semanticNet net = new semanticNet();

        System.out.println("\n=== Adding Relations ===");
        net.addRelation("Cats", "are", "mammals");
        net.addRelation("Momo", "is a", "cat");
        net.addRelation("Momo", "is owned by", "Su");
        net.addRelation("Momo", "likes", "fish");
        net.addRelation("Momo", "likes", "food");
        net.addRelation("Momo", "has", "fur");
        net.addRelation("Momo", "fur is", "black");
        net.addRelation("Jojo", "is a", "fish");
        net.addRelation("Jojo", "is owned by", "Su");
        net.addRelation("Jojo", "is", "blue");
        net.addRelation("Jojo", "is scared of", "Momo");
        net.addRelation("Su", "is a", "human");

        System.out.println("\n=== Printing Network ===");
        net.printNetwork();

        System.out.println("\n=== Searching Relations ===");
        net.searchRelation("Momo", "likes", "fish");
        net.searchRelation("Momo", null, null);
        net.searchRelation("Jojo", "is scared of", "Momo");

        System.out.println("\n=== Deleting a Relation ===");
        net.delRelation("Momo", "likes", "food");
        net.searchRelation("Momo", null, null);

        System.out.println("\n=== Deleting a Node ===");
        net.delNode("Jojo");
        net.printNetwork();

        System.out.println("\n=== Inferring Relations ===");
        net.inferRelations();
        net.printNetwork();
    }
}
