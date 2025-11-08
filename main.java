package code_garage.academic.assignments.cpcs331.SemNet;

public class main{
    public static void main(String[] args) {
        semanticNet net = new semanticNet();
        
        net.addRelation("Cats", "are", "Mammals");
        net.addRelation("Momo", "is a", "Cat");
        net.addRelation("Momo", "owned by", "Su");
        net.addRelation("Momo", "likes", "Fish");
        net.addRelation("Momo", "likes", "Food");
        net.addRelation("Momo", "has", "Fur");
        net.addRelation("Fur", "color", "Black");
        net.addRelation("Jojo", "is a", "Fish");
        net.addRelation("Jojo", "owned by", "Su");
        net.addRelation("Jojo", "is", "Blue");
        net.addRelation("Jojo", "scared of", "Momo");
        net.addRelation("Su", "is a", "Human");

        System.out.println("\nThe whole semantic network\n");
        net.printNetwork();

        System.out.println("\nSearching for 'Su is a human' after deleting it\n");
        net.delRelation("Su", "is a", "Human");
        net.searchRelation("Su", "is a", "Human");

        System.out.println("\nAdding 'Su is a cat'\n");
        net.addRelation("Su", "is a", "cat");
        
        System.out.println("\nThe whole semantic network\n");
        net.printNetwork();
    }
}
