package tb.soft;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.*;

public class Collections {
        private Set<Person> hashSet1;
        private Set<EqualsHash> hashSet2;

        private Set<Person> treeSet1;
        private Set<EqualsHash> treeSet2;

        private List<Person> arrayList1;
        private List<EqualsHash> arrayList2;

        private List<Person> linkedList1;
        private List<EqualsHash> linkedList2;

        private Map<Person, Integer> hashMap1;
        private Map<EqualsHash, Integer> hashMap2;

        private Map<Person, Integer> treeMap1;
        private Map<EqualsHash, Integer> treeMap2;

        public Collections(){
            hashSet1 = new HashSet<>();
            hashSet2 = new HashSet<>();
            treeSet1 = new TreeSet<>();
            treeSet2 = new TreeSet<>();
            arrayList1 = new ArrayList<>();
            arrayList2 = new ArrayList<>();
            linkedList1 = new LinkedList<>();
            linkedList2 = new LinkedList<>();
            hashMap1 = new HashMap();
            hashMap2 = new HashMap<>();
            treeMap1 = new TreeMap<>();
            treeMap2 = new TreeMap<>();
        }

        public void add(Person person) throws PersonException{
            EqualsHash personEqualsHash = new EqualsHash(person);
            hashSet1.add(person);
            hashSet2.add(personEqualsHash);
            treeSet1.add(person);
            treeSet2.add(personEqualsHash);
            arrayList1.add(person);
            arrayList2.add(personEqualsHash);
            linkedList1.add(person);
            linkedList2.add(personEqualsHash);
            hashMap1.put(person,hashMap1.size()+1 );
            hashMap2.put(personEqualsHash, hashMap2.size()+1);
            treeMap1.put(person, treeMap1.size()+1);
            treeMap2.put(personEqualsHash, treeMap2.size()+1);
        }

        public void remove(Person person) throws PersonException{
            EqualsHash personEqualsHash = new EqualsHash(person);
            hashSet1.remove(person);
            hashSet2.remove(personEqualsHash);
            treeSet1.remove(person);
            treeSet2.remove(personEqualsHash);
            arrayList1.remove(person);
            arrayList2.remove(personEqualsHash);
            linkedList1.remove(person);
            linkedList2.remove(personEqualsHash);
            hashMap1.remove(person);
            hashMap2.remove(personEqualsHash);
            treeMap1.remove(person);
            treeMap2.remove(personEqualsHash);
        }

        public Set<Person> getHashSet1() {
            return hashSet1;
        }

        public Set<EqualsHash> getHashSet2() {
            return hashSet2;
        }

        public Set<Person> getTreeSet1() {
            return treeSet1;
        }

        public Set<EqualsHash> getTreeSet2() {
            return treeSet2;
        }

        public List<Person> getArrayList1() {
            return arrayList1;
        }

        public List<EqualsHash> getArrayList2() {
            return arrayList2;
        }

        public List<Person> getLinkedList1() {
            return linkedList1;
        }

        public List<EqualsHash> getLinkedList2() {
            return linkedList2;
        }

        public Map<Person, Integer> getHashMap1() {
            return hashMap1;
        }

        public Map<EqualsHash, Integer> getHashMap2() {
            return hashMap2;
        }

        public Map<Person, Integer> getTreeMap1() {
            return treeMap1;
        }

        public Map<EqualsHash, Integer> getTreeMap2() {
            return treeMap2;
        }}