package symbolics.division.flopster;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.Direction;

public class FlopsterDrive extends HorizontalFacingBlock {

    public static final MapCodec<FlopsterDrive> CODEC = AbstractBlock.createCodec(FlopsterDrive::new);

    public FlopsterDrive(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(
                HorizontalFacingBlock.FACING, Direction.NORTH
        ));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    public void accept() {

    }

}
