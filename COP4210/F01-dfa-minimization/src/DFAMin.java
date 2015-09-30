import java.util.*;

/**
 * Created by Ricardo Vasquez on 9/23/15.
 *
 * I followed the algorithm demonstrated in this gif
 * http://www.cs.ucf.edu/~dmarino/ucf/cot4210/lec/Lec14DFA_Minimization.gif
 *
 */
class DFA {
    private final int numStates;
    private final int alphabetSize;
    private final int[] aStates;
    private final int[] rStates;
    private final int[][] transitions;

    public DFA(int numStates, int alphabetSize, int[] aStates, int[][] transitions) {
        this.numStates = numStates;
        this.alphabetSize = alphabetSize;
        this.aStates = aStates;
        this.rStates = getRejectStates(aStates, numStates);
        this.transitions = transitions;
    }

    /* Given a set of accept states, determine the reject states from the missing states */
    public static int[] getRejectStates(int[] aStates, int numStates) {
        ArrayList<Integer> rStatesList = new ArrayList();
        ArrayList<Integer> aStatesList = new ArrayList();
        for (int i : aStates) {
            aStatesList.add(i);
        }
        for(int i = 0; i < numStates; i++) {
            if (!aStatesList.contains(i))
                rStatesList.add(i);
        }
        int[] rStatesArray = new int[rStatesList.size()];
        for (int i = 0; i < rStatesArray.length; i++) {
            rStatesArray[i] = rStatesList.get(i).intValue();
        }
        return rStatesArray;
    }

    public int[] getAStates() {
        return this.aStates;
    }
    public int[] getRStates() {
        return this.rStates;
    }
    public int[][] getTransitions() {
        return this.transitions;
    }
    public int getAlphabetSize() {
        return this.alphabetSize;
    }
    public int getNumStates() { return this.numStates; }

}
public class DFAMin {

    /* Builds an array of DFA from standard in */
    public static DFA[] buildDFAArray() {
        Scanner scan = new Scanner(System.in);

        // Scan in the number of DFAs
        int numDFA = scan.nextInt();

        // Initialize array
        DFA dfaArray[] = new DFA[numDFA];

        // Build DFAs
        for (int n = 0; n < numDFA; n++) {

            // Scan in states and alpha size
            int numStates = scan.nextInt();
            int alphabetSize = scan.nextInt();

            // Scan in accept states
            int numAcceptStates = scan.nextInt();
            int[] aStates = new int[numAcceptStates];
            for (int a = 0; a < numAcceptStates; a++) {
                aStates[a] = scan.nextInt();
            }

            // Scan in transitions
            int[][] transitions = new int[numStates][alphabetSize];
            for (int i = 0; i < numStates; i++) {
                for (int j = 0; j < alphabetSize; j++) {
                    transitions[i][j] = scan.nextInt();
                }
            }

            // Build DFA
            dfaArray[n] = new DFA(numStates, alphabetSize, aStates, transitions);
        }
        return dfaArray;
    }

    /* Minimizes a DFA and returns the minimized DFA
    *  Follows the steps of the algorithm
    * */
    public static DFA minimizeDFA(DFA dfa) {

        // Step 1 Find all pairs
        ArrayList<Set> acceptPairs = findPairs(dfa.getAStates());
        ArrayList<Set> rejectPairs = findPairs(dfa.getRStates());

        // Step 2 Build the set of pairs that go to set of states of the same type
        Map<Set, Set[]> acceptMap = getNewTransitionMap(acceptPairs, dfa, true);
        Map<Set, Set[]> rejectMap = getNewTransitionMap(rejectPairs, dfa, false);

        // Step 3 Merge, simplify and build new transition table
        acceptMap = mergeStatesInMap(acceptMap);
        rejectMap = mergeStatesInMap(rejectMap);
        acceptMap.putAll(rejectMap);
        Map<Integer, Integer> associationMap = buildAssociationMap(acceptMap);

        // Get minimized accept states
        int[] minAStates = buildNewAStates(associationMap, dfa.getAStates());

        // Build minimized transition table
        int[][] newTransitionTable = buildNewTransitionTable(acceptMap, dfa.getAlphabetSize(), associationMap);

        // Build minimized DFA
        return new DFA(newTransitionTable.length, dfa.getAlphabetSize(), minAStates, newTransitionTable);
    }

    /* Builds a set of accept states using an associationMap to minimze the original accept states */
    public static int[] buildNewAStates(Map<Integer, Integer> associationMap, int[] aStates) {
        Set aStatesSet = new LinkedHashSet<>();
        for(int i = 0; i < aStates.length; i++) {
            if(associationMap.containsKey(aStates[i]))
                aStatesSet.add(associationMap.get(aStates[i]));
            else
                aStatesSet.add(aStates[i]);
        }
        int[] minAStates = new int[aStatesSet.size()];
        int i = 0;
        for (Object state : aStatesSet) {
            minAStates[i] = (int)state;
            i++;
        }
        return minAStates;
    }

    /* Builds a map that associates states with another state number when minimizing */
    public static Map<Integer, Integer> buildAssociationMap(Map<Set, Set[]> totalMap) {

        Map<Integer, Integer> associationMap = new LinkedHashMap<>();

        Set<Set> setOfStateSets = totalMap.keySet();
        Iterator<Set> setIterator = setOfStateSets.iterator();
        ArrayList<Integer> stateIndicesGreaterThanMapSize = new ArrayList();
        // Map the ith state in the stateSet to the first state
        while(setIterator.hasNext()) {
            Object[] stateSetArray = setIterator.next().toArray();
            for (int i = 0; i < stateSetArray.length; i++) {
                if(i != 0)
                    associationMap.put((Integer)stateSetArray[i], (Integer)stateSetArray[0]);
                if((Integer)stateSetArray[i] >= totalMap.size()) {
                    stateIndicesGreaterThanMapSize.add((Integer)stateSetArray[i]);
                }
            }
        }

        // Map states with indices greater than the map size to a respective index
        // and are not in the association map
        Collections.sort(stateIndicesGreaterThanMapSize);
        for (int i : stateIndicesGreaterThanMapSize) {
            if(!associationMap.containsKey(i)) {
                // Determine the highest key in the map and place the max + 1 as the value of
                // this i key
                int max = 0;
                for (Object key : associationMap.values().toArray()) {
                    if (max < (Integer)key){
                        max = (Integer)key;
                    }
                }
                associationMap.put(i, max + 1);
            }
        }
        return associationMap;
    }

    /* Builds a minimized transition table from a map of the transitions */
    public static int[][] buildNewTransitionTable(Map<Set, Set[]> totalMap, int alphabetSize, Map<Integer, Integer> associationMap) {

        int[][] newTransitionTable = new int[totalMap.size()][alphabetSize];

        for (Map.Entry<Set, Set[]> entry : totalMap.entrySet()) {

            // Get the index of the state
            int stateIndex;
            Object[] stateArray = entry.getKey().toArray();
            if (associationMap.containsKey(stateArray[0])) {
                stateIndex = associationMap.get(stateArray[0]);
            } else {
                stateIndex = (Integer)stateArray[0];
            }

            // Use the state index to place the tranitions in the correct
            // location in the transition table
            Set[] setOfTransitions = entry.getValue();
            for (int i = 0; i < setOfTransitions.length; i++) {
                int transitionState;
                Object[] transitionArray = setOfTransitions[i].toArray();

                // Check if association mapping needs to happen
                if (associationMap.containsKey(transitionArray[0])) {
                    transitionState = associationMap.get(transitionArray[0]);
                } else {
                    transitionState = (Integer)transitionArray[0];
                }

                // Place in transition table
                newTransitionTable[stateIndex][i] = transitionState;
            }
        }

        return newTransitionTable;
    }

    /* Merges together repeated states in the transition map */
    public static Map<Set, Set[]> mergeStatesInMap(Map<Set, Set[]> transitionMap) {

        Set<Set> setOfStateSets = transitionMap.keySet();
        int indexOfStateSet = 0;
        // Iterate through each set of state sets
        while(transitionMap.size() > indexOfStateSet) {
            Iterator<Set> setIterator = setOfStateSets.iterator();
            Set stateSet = setIterator.next();

            // For each int state in the set
            for (Object i : stateSet.toArray()) {

                // For each other set that isn't the stateSet
                for (int k = 0; k < setOfStateSets.size(); k++) {
                    Set otherSet = (Set)setOfStateSets.toArray()[k];
                    if (otherSet != stateSet) {

                        // If this other set contains a set in the stateSet
                        if (otherSet.contains(i)) {

                            // Merge Transitions
                            Object[] stateSetTransitionsArray = transitionMap.values().toArray();
                            Set[] stateSetTransitions = (Set[])stateSetTransitionsArray[indexOfStateSet];
                            Set[] otherSetTransitions = transitionMap.get(otherSet);
                            Set[] mergedSetTransitions = new LinkedHashSet[stateSetTransitions.length];
                            for (int j = 0; j < mergedSetTransitions.length; j++) {
                                mergedSetTransitions[j] = new LinkedHashSet<>();
                                mergedSetTransitions[j].addAll(stateSetTransitions[j]);
                                mergedSetTransitions[j].addAll(otherSetTransitions[j]);
                            }

                            // Merge state Sets
                            stateSet.addAll(otherSet);

                            // Remove the merged state Set and it's transitions from map
                            transitionMap.remove(otherSet);

                            // Put back into Map
                            transitionMap.replace(stateSet, mergedSetTransitions);
                        }
                    }
                }
            }
            indexOfStateSet++;
        }

        return transitionMap;
    }

    /* Determines if two states both go to the same type of state */
    public static boolean isSameType(int stateX, int stateY, int[] aStates) {
        ArrayList<Integer> aStatesList = new ArrayList();
        for (int i : aStates) {
            aStatesList.add(i);
        }
        // if stateX && stateY are BOTH in aStates then return true
        if (aStatesList.contains(stateX) && aStatesList.contains(stateY)) {
            return true;
        // if stateX && stateY are BOTH not in aStates then return true
        } else if (!aStatesList.contains(stateX) && !aStatesList.contains(stateY)) {
            return true;
        } else
            // States are of different types
            return false;
    }

    /* Returns a Map that holds a pair of Set which is the state pair, and the array of state Sets that the state pair moves to */
    public static Map<Set, Set[]> getNewTransitionMap(ArrayList<Set> pairs, DFA dfa, boolean isPairAcceptStates) {

        Map<Set, Set[]> newTransitionMap = new LinkedHashMap<>();

        int[][] transitions = dfa.getTransitions();
        int alphabetSize = dfa.getAlphabetSize();

        // Determine if the states are accept or reject
        int[] states = dfa.getAStates();
        if (isPairAcceptStates)
            states = dfa.getAStates();
        else
            states = dfa.getRStates();

        // Make a list of the states and remove them as they are added to the Map
        ArrayList statesList = new ArrayList();
        for (int i : states) {
            statesList.add(i);
        }

        for (Set<Integer> s : pairs) {

            Iterator setIterator = s.iterator();

            // newTransition is the array of same type state Sets that a pair transitions to
            // The index of the array determines the letter read in for the transition
            Set[] newTransition = new LinkedHashSet[alphabetSize];

            // stateX is the first state in the pair
            int stateX = (int) setIterator.next();

            // stateY is the second state in the pair

            int stateY = -1;
            if (s.size() > 1)
                stateY = (int) setIterator.next();

            boolean canBePaired = true;

            // Check if both states go to the same type
            // Loop will break whenever the states don't go to the same type or the state is alone
            for (int i = 0; i < alphabetSize && canBePaired == true && stateY != -1; i++) {
                canBePaired = isSameType(transitions[stateX][i], transitions[stateY][i], states);
            }

            // If they can be paired, make a set containing the states the can move to for each letter in the alphabet
            if (canBePaired) {
                // Create array of set transitions
                for (int i = 0; i < alphabetSize; i++) {

                    // Create a set of states
                    Set<Integer> newState = new LinkedHashSet<>();
                    newState.add(transitions[stateX][i]);
                    if(s.size() > 1)
                        newState.add(transitions[stateY][i]);

                    newTransition[i] = newState;
                }
            }

            // If the states go to the same type, wrap the states and tranisitions into a key, value pair and
            // put them into the stateTransitionMap, then add it to the newTransitionMap list
            if (newTransition[0] != null) {

                Set<Integer> keyState = new LinkedHashSet<>();
                keyState.add(stateX);
                if(s.size() > 1)
                    keyState.add(stateY);

                // Remove states from statesList
                if (statesList.contains(stateX))
                    statesList.remove(statesList.indexOf(stateX));
                if (statesList.contains(stateY))
                    statesList.remove(statesList.indexOf(stateY));

                newTransitionMap.put(keyState, newTransition);
            }
        }

        // Add any missing states to the Map
        if (!statesList.isEmpty()) {

            for (Object i : statesList) {
                Set keyState = new LinkedHashSet<>();
                keyState.add(i);

                Set[] newTransition = new LinkedHashSet[alphabetSize];
                for (int j = 0; j < newTransition.length; j++) {
                    newTransition[j] = new LinkedHashSet<>();
                    newTransition[j].add(transitions[(int) i][j]);
                }

                newTransitionMap.put(keyState, newTransition);
            }
        }

        return newTransitionMap;
    }

    /* finds all the combinations of state pairs between states of the same type */
    public static ArrayList<Set> findPairs(int[] states) {
        ArrayList<Set> pairs = new ArrayList();
        if (states.length > 1) {
            // Calculate states.length choose 2
            //int numCombinations = factorial(states.length) / (2 * factorial(states.length - 2));
            for (int i = 0; i < states.length - 1; i++) {
                for (int j = i + 1; j < states.length; j++) {

                    // Add the pair of states as a set
                    Set pair = new LinkedHashSet<>();
                    pair.add(states[i]);
                    pair.add(states[j]);

                    // Add the set of states to the list of set states
                    pairs.add(pair);
                }
            }
        } else if (states.length == 1) {
            // If there’s 1 state, let it be by itself
            Set loneState = new LinkedHashSet<>();
            loneState.add(states[0]);
            pairs.add(loneState);
        }
        // If there’s no states, return an empty array
        return pairs;

    }

    /* Prints an array of DFAs to standard out */
    public static void printDFAArray(DFA[] dfaArray) {
        int count = 1;
        for (DFA dfa : dfaArray) {
            System.out.println("DFA #" + count);
            System.out.println(dfa.getNumStates() + " " + dfa.getAlphabetSize());
            StringBuilder sb = new StringBuilder();
            for (int i : dfa.getAStates()) {
                sb.append(i + " ");
            }
            System.out.println(dfa.getAStates().length + " " + sb.toString());
            int[][] transitions = dfa.getTransitions();
            for (int[] intArray : transitions) {
                for (int i : intArray) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println();
            count++;
        }
    }

    public static void main(String[] args) {

        DFA[] dfaArray = buildDFAArray();
        for (int i = 0; i < dfaArray.length; i++) {
            dfaArray[i] = minimizeDFA(dfaArray[i]);
        }

        printDFAArray(dfaArray);
    }
}
