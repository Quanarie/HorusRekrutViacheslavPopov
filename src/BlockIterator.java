import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BlockIterator implements Iterator<Block> {
    private Stack<Block> stack = new Stack<>();

    public BlockIterator(List<Block> blocks) {
        AddBlocksToStack(blocks);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Block next() {
        Block block = stack.pop();
        if (block instanceof CompositeBlock) {
            AddBlocksToStack(((CompositeBlock) block).getBlocks());
        }
        return block;
    }

    private void AddBlocksToStack(List<Block> blocks) {
        blocks.forEach(block -> stack.push(block));
    }
}
