import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String redColor = "Red";
        String blueColor = "Blue";

        String brickMaterial = "Brick";
        String paperMaterial = "Paper";

        List<Block> blockList = new ArrayList<>();
        blockList.add(new BlockImpl(redColor, paperMaterial));
        blockList.add(new BlockImpl(redColor, brickMaterial));
        blockList.add(new BlockImpl(redColor, paperMaterial));

        CompositeBlockImpl compositeBlock = new CompositeBlockImpl(redColor, paperMaterial, new ArrayList<>());
        compositeBlock.getBlocks().add(new BlockImpl(blueColor, brickMaterial));
        compositeBlock.getBlocks().add(new BlockImpl(redColor, paperMaterial));
        blockList.add(compositeBlock);

        Wall wall = new Wall(blockList);

        System.out.println(wall.findBlockByColor(blueColor).get().getMaterial());
    }
}