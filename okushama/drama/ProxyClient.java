package okushama.drama;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ProxyClient extends ProxyCommon {

	@Override
	public void onModInit(FMLInitializationEvent evt) {
		tickHandler = new TickHandlerClient();
		TickRegistry.registerTickHandler(tickHandler, Side.CLIENT);
		super.onModInit(evt);
	}

}
