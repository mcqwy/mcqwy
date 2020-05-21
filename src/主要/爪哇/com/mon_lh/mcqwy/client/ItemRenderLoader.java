package com.mon_lh.mcqwy.client;

import com.mon_lh.mcqwy.block.BlockLoader;
import com.mon_lh.mcqwy.item.ItemLoader;

public class ItemRenderLoader {
	
	public ItemRenderLoader()
    {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
    }

}
