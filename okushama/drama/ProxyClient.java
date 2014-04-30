package okushama.drama;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ProxyClient extends ProxyCommon {

	@Override
	public void onModInit(FMLInitializationEvent evt) {
		tickHandler = new TickHandlerClient();
		FMLCommonHandler.instance().bus().register(tickHandler);
		super.onModInit(evt);
	}

}
