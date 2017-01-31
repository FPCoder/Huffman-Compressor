
/**
 * Interface for creating a table of codings from a tree.
 * Client passes an ITreeMaker to the makeTable method, which
 * initializes state so that subsequent calls on getCode will
 * return the "010111010" huffman coding for a particular "character".
 * <P>
 * Note that int values should be used for every chunk/character.
 * <P>
 * @author Owen Astrachan
 *
 */
public interface IHuffEncoder {
    /**
     * Initialize state from a tree, the tree is obtained
     * from the treeMaker.
     * @param treeMaker used to generate tree for creating table/map.
     */
    public void makeTable(ITreeMaker treeMaker);
    
    /**
     * Returns coding, e.g., "010111" for specified chunk/character. It
     * is an errot to call this method before makeTable has been
     * called.
     * @param i is the chunk for which the coding is returned
     * @return the huff encoding for the specified chunk
     */
    public String getCode(int i);
}
