package okushama.drama;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler {

	public boolean hasSet = false;

	public String asieSplash() {
		return getFirstLineOfSite("http://asie.pl/drama.php?plain");

	}

	public String asieCount() {
		return getFirstLineOfSite("http://asie.pl/drama.php?count");
	}

	public String getFirstLineOfSite(String url){
		try {
			URL ourl = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(ourl.openStream()));
			String txt;
			while ((txt = in.readLine()) != null) {
				return txt;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "missingno.";
	}

	public int countCache = -1;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.CLIENT)) {
			try {
				Minecraft mc = Minecraft.getMinecraft();
				if (mc.currentScreen != null) {
					if (mc.currentScreen instanceof GuiMainMenu) {
						GuiMainMenu mm = (GuiMainMenu)mc.currentScreen;
						if (!hasSet) {
							ObfuscationReflectionHelper.setPrivateValue(GuiMainMenu.class, mm, asieSplash(), "splashText" ,"field_73975_c", "r");
							countCache = Integer.parseInt(asieCount());
							hasSet = true;
						}
					}
					else {
						hasSet = false;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.currentScreen != null) {
				if (mc.currentScreen instanceof GuiMainMenu) {
					GuiMainMenu mm = (GuiMainMenu)mc.currentScreen;
					mm.drawString(mc.fontRenderer, countCache+" dramas generated and counting!", 2, 2, 0xffffff);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Drama";
	}

}
