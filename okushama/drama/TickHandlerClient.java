package okushama.drama;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;

public class TickHandlerClient {

	public boolean hasSet = false;

	public String asieSplash() {
		return getFirstLineOfSite("http://asie.pl/drama.php?plain");

	}

	public String asieCount() {
		return getFirstLineOfSite("http://asie.pl/drama.php?count");
	}

	public String getFirstLineOfSite(String url) {
		try {
			URL ourl = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ourl.openStream()));
			String txt;
			while ((txt = in.readLine()) != null) {
				return txt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "missingno.";
	}

	public int countCache = -1;

	@SubscribeEvent
	public void start(RenderTickEvent event) {
		if (event.side.equals(Side.CLIENT)) {
			tickStart();
			tickEnd();
		}
	}

	public void tickStart() {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.currentScreen != null) {
				if (mc.currentScreen instanceof GuiMainMenu) {
					GuiMainMenu mm = (GuiMainMenu) mc.currentScreen;
					if (!hasSet) {
						ObfuscationReflectionHelper.setPrivateValue(
								GuiMainMenu.class, mm, asieSplash(),
								"splashText", "field_73975_c", "r");
						countCache = Integer.parseInt(asieCount());
						hasSet = true;
					}
				} else {
					hasSet = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tickEnd() {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.currentScreen != null) {
				if (mc.currentScreen instanceof GuiMainMenu) {
					GuiMainMenu mm = (GuiMainMenu) mc.currentScreen;
					mm.drawString(mc.fontRenderer, countCache
							+ " dramas generated and counting!", 2, 2, 0xffffff);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
