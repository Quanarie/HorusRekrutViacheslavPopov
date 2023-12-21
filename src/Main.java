import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String redColor = "Red";
        String blueColor = "Blue";

        String brickMaterial = "Brick";
        String paperMaterial = "Paper";

        List<Block> blockList = new ArrayList<>();
        blockList.add(new BlockImpl(blueColor, paperMaterial));
        blockList.add(new BlockImpl(blueColor, brickMaterial));
        blockList.add(new BlockImpl(blueColor, paperMaterial));

        CompositeBlockImpl compositeBlock = new CompositeBlockImpl(blueColor, paperMaterial, new ArrayList<>());
        compositeBlock.getBlocks().add(new BlockImpl(blueColor, brickMaterial));
        compositeBlock.getBlocks().add(new BlockImpl(redColor, paperMaterial));

        CompositeBlockImpl compositeBlock2 = new CompositeBlockImpl(blueColor, paperMaterial, new ArrayList<>());
        compositeBlock2.getBlocks().add(compositeBlock);

        blockList.add(compositeBlock2);

        Wall wall = new Wall(blockList);

        System.out.println(wall.count());

        for (Block block : wall.findBlocksByMaterial(brickMaterial)) {
            System.out.println(block.getColor() + " " + block.getMaterial());
        }
    }
}