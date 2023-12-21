import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        BlockIterator iterator = new BlockIterator(blocks);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (color.equals(block.getColor())) {
                return Optional.of(block);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        BlockIterator iterator = new BlockIterator(blocks);
        List<Block> res = new ArrayList<>();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (material.equals(block.getMaterial())) {
                res.add(block);
            }
        }

        return res;
    }

    @Override
    public int count() {
        BlockIterator iterator = new BlockIterator(blocks);
        int res = 0;
        while (iterator.hasNext()) {
            iterator.next();
            res++;
        }
        return res;
    }
}