
/**
 * Abstraction/interface to allow trees to be made in more
 * than one setting, and to export the treemaker as an object.
 * For example, a tree can be made from character-counts using
 * a priority queue. Alternatively, a tree could be made from
 * an input stream when unhuffing if the huff process stored the
 * tree in the file in some format.
 * <P>
 * Typically classes that implement this interface will supply
 * state to the class via a constructor or some other method so
 * that makeRoot will work (since makeRoot has no parameters).
 * 
 * @author Owen Astrachan
 */
public interface ITreeMaker {
    /**
     * Return the root of a Huffman/coding tree.
     * @return root of a tree
     */
    public TreeNode makeRoot();
}
