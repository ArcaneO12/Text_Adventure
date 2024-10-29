import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAdventure {

    public static void main(String[] args) {
        System.out.println(
                "Type a cardinal direction (N/S/E/W) to move, LOOK to see your surroundings, INVENTORY to see your items, TAKE _ITEM_ to pick up an item, USE _ITEM_ to use it, and HELP for a reminder of these commands. Note: Commands to not have to be completed or capitalized.\n");
        // * Message at beginning of game
        System.out.println("You begin in the first room. The second room lies to your north.");
        while (true) {
            // Player input
            Scanner sc = new Scanner(System.in);
            System.out.print("-> ");
            String act = sc.nextLine().toLowerCase().trim();
            act = correctDirs(act);
            System.out.println();

            newRoom = paths.get(room).get(act); // If the player chose to move
            if (act.equals("")) { // If the player pressed enter after only typing spaces
                System.out.println("You try to make up your mind.");
                act = "";
            } else if (newRoom != null) { // Moves the player if the player so chose
                room = newRoom;
                System.out.println("*" + room.toUpperCase() + "*");
                if (!seen.contains(room)) {
                    System.out.println(rooms.get(room));
                }
                seen.add(room);
                act = "";
            } else if ("look".startsWith(act)) { // If the player wants a reminder of their surroundings
                System.out.println(rooms.get(room));
                act = "";
            } else if ("inventory".startsWith(act)) { // If the player wants to look at their inventory
                System.out.println(i);
                act = "";
            } else if ("help".startsWith(act)) {
                System.out.println("(NORTH/SOUTH/EAST/WEST), LOOK, INVENTORY, TAKE, USE, HELP");
                act = "";
            } else if (dirs.contains(act)) { // Tells the player if they chose to move in a blocked direction
                System.out.println("You cannot go " + act + " from here.");
                act = "";
            }

            // * Item details
            act = itemHandling("item", "three", "two", "You used the item! Nothing happened.", act);
            act = itemHandling("tool", "two", "one", "You used the tool! Nothing happened.", act);

            if (act != "") {
                System.out.println("You cannot " + act + " here."); // If player input is unrecognizable
            }
        }
    }

    // * Starting room
    private static String room = "one";

    // All vowels
    private static String vowels = "aeiou";

    // Value of the next room
    private static String newRoom = null;

    // Every direction
    private static Set<String> dirs = Set.of("north", "south", "east", "west");

    // * The places the player can move and the directions that will take them there
    private static final Map<String, Map<String, String>> paths = Map.of(
            "one", Map.of("north", "two"),
            "two", Map.of("south", "one",
                    "east", "three"),
            "three", Map.of("west", "two"));

    // * Room descriptions
    private static final Map<String, String> rooms = Map.of(
            "one", "You are in the first room. The second room lies to your north. You can use a tool here.",
            "two", "You are in the second room. The first room lies to your south, and the third room lies to your east. You can use an item here.",
            "three", "You are in the third room.");

    // * Item descriptions
    private static final Map<String, String> items = Map.of(
            "item", "An item",
            "tool", "A tool");

    // This ser contains rooms the player has visited
    private static Set<String> seen = new HashSet<String>();

    // This ser contains the player's inventory
    private static Set<String> i = new HashSet<String>();

    // This ser contains the items that have been used
    private static Set<String> used = new HashSet<String>();

    // Corrects the input the player gave to a cardinal direction
    static String correctDirs(String act) {
        if (!act.isEmpty()) {
            for (String dir : dirs) {
                if (dir.startsWith(act)) {
                    return dir;
                }
            }
        }
        return act;
    }

    private static String itemHandling(String item, String rm1, String rm2, String resultOfUse, String act) {
        if (act.endsWith(" " + item)) { // Checks if player chose to interact with item
            int itemValue = act.indexOf(item); // wohufiqukrwf
            act = act.substring(0, itemValue - 1);
            if ("use".startsWith(act) && room.equals(rm2)) { // Use item
                used.add(item);
                System.out.println(resultOfUse);
            } else if ("look".startsWith(act) && i.contains(item)) { // Look item
                System.out.println(items.get(item));
            } else if ("take".startsWith(act) && room.equals(rm1)) {
                i.add(item);
                System.out.println("You take the " + item + ".");
            } else {
                System.out.println("you cannot " + act + " the " + item + ".");
            }
            return "";
        } else if (act.equals("look") || newRoom != null) {
            if (room.equals(rm1) && !(i.contains(item) || used.contains(item))) {
                // A vs An. Tells player that item exists.
                if (vowels.contains(item.substring(0, 1))) {
                    System.out.println("There is an " + item + " here.");
                } else {
                    System.out.println("There is a " + item + " here.");
                }
            }
            return "";
        }
        return act;
    }
}
    
// * == Something that changes depending on the gamen