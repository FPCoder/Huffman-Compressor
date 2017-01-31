
/**
 * Main/launch program for Huff assignment. A better
 * comment than this is warranted.
 * @author Evan Jones
 *
 */
public class Huff {

    public static void main(String[] args){
        HuffViewer sv = new HuffViewer("Huffing Coding");
        HuffModel hm = new HuffModel();
        BitInputStream bt = new BitInputStream("test.txt");
        TreeMaker tree = new TreeMaker();
        Encoder cd = new Encoder();
        
        sv.setModel(hm);
        hm.setViewer(sv);
        hm.initialize(bt);
        tree.initialize(hm.getChars());
        hm.setCodes(tree.getCodes());
        cd.makeTable(tree);
        
    }
}
