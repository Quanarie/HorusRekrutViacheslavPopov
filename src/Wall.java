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
        return traverse(blocks,
                new TraverseOptions<>(
                        Optional.empty(),
                        (block, result) -> {
                            if (color.equals(block.getColor())) return Optional.of(block);
                            return Optional.empty();
                        },
                        Optional::isPresent
                )
        );
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return traverse(blocks,
                new TraverseOptions<>(
                        new ArrayList<>(),
                        (block, result) -> {
                            if (material.equals(block.getMaterial())) result.add(block);
                            return result;
                        },
                        result -> false
                )
        );
    }

    @Override
    public int count() {
        return traverse(blocks,
                new TraverseOptions<>(
                        0,
                        (block, result) -> result + 1,
                        result -> false
                )
        );
    }

    private <T> T traverse(List<Block> blocks, TraverseOptions<T> options) {
        T result = options.initialValue;
        for (Block block : blocks) {
            result = traverse(block, options.withInitial(result));
            if (options.breakCondition.test(result)) {
                return result;
            }
        }
        return result;
    }

    private <T> T traverse(Block block, TraverseOptions<T> options) {
        T result = options.initialValue;
        result = options.function.apply(block, result);
        if (options.breakCondition.test(result)) {
            return result;
        }

        if (block instanceof CompositeBlock) {
            return traverse(((CompositeBlock) block).getBlocks(), options.withInitial(result));
        }
        return result;
    }

    record TraverseOptions<T>(T initialValue, TraverseFunction<T> function, Predicate<T> breakCondition) {
        TraverseOptions<T> withInitial(T initialValue) {
            return new TraverseOptions<>(initialValue, this.function, this.breakCondition);
        }
    }

    interface TraverseFunction<T> {
        T apply(Block block, T result);
    }
}