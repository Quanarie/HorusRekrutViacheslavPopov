import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .flatMap(this::getAllBlocks)
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .flatMap(this::getAllBlocks)
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .flatMap(this::getAllBlocks)
                .mapToInt(block -> 1)
                .sum();
    }

    private Stream<Block> getAllBlocks(Block block) {
        if (block instanceof CompositeBlock) {
            return Stream.concat(Stream.of(block),
                    ((CompositeBlock) block).getBlocks().stream().flatMap(this::getAllBlocks));
        } else {
            return Stream.of(block);
        }
    }
}