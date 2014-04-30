package okushama.drama;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "drama", name = "Minecraft Drama Generator Splash", version = "0.1")
public class Drama {

	@Instance("drama")
	public static Drama instance;

	@SidedProxy(serverSide = "okushama.drama.ProxyCommon", clientSide = "okushama.drama.ProxyClient")
	public static ProxyCommon proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.onModInit(event);
	}
}
