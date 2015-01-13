package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LoreItem extends RootItem
{
	/*
	 * String title; String author;
	 * 
	 * public LoreItem(String title, String author, File loreFile) {
	 * this.setMaxStackSize(1); this.title = title; this.author = author; }
	 * 
	 * @Override public String getItemStackDisplayName(ItemStack stack) { if
	 * (stack.hasTagCompound()) { NBTTagCompound nbttagcompound =
	 * stack.getTagCompound(); String s = nbttagcompound.getString("title");
	 * 
	 * if (!StringUtils.isNullOrEmpty(s)) { return s; } }
	 * 
	 * return super.getItemStackDisplayName(stack); }
	 * 
	 * public static boolean validBookTagContents(NBTTagCompound p_77828_0_) {
	 * if (!ItemWritableBook.func_150930_a(p_77828_0_)) { return false; } else
	 * if (!p_77828_0_.hasKey("title", 8)) { return false; } else { String s =
	 * p_77828_0_.getString("title"); return s != null && s.length() <= 16 ?
	 * p_77828_0_.hasKey("author", 8) : false; } }
	 * 
	 * /** allows items to add custom lines of information to the mouseover
	 * description
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public void addInformation(ItemStack p_77624_1_,
	 * EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) { if
	 * (p_77624_1_.hasTagCompound()) { NBTTagCompound tag =
	 * p_77624_1_.getTagCompound(); tag.setString("title", title);
	 * tag.setString("author", author); String s = tag.getString("author");
	 * 
	 * if (!StringUtils.isNullOrEmpty(s)) {
	 * p_77624_3_.add(EnumChatFormatting.GRAY +
	 * StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] { s
	 * })); } } }
	 * 
	 * /** Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 * 
	 * @Override public ItemStack onItemRightClick(ItemStack p_77659_1_, World
	 * p_77659_2_, EntityPlayer p_77659_3_) {
	 * p_77659_3_.displayGUIBook(p_77659_1_); return p_77659_1_; }
	 * 
	 * /** If this function returns true (or the item is damageable), the
	 * ItemStack's NBT tag will be sent to the client.
	 * 
	 * @Override public boolean getShareTag() { return true; }
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public boolean hasEffect(ItemStack p_77636_1_) {
	 * return true; }
	 * 
	 * private String readFile(String file) throws IOException { BufferedReader
	 * reader = new BufferedReader(new FileReader(file)); String line = null;
	 * StringBuilder stringBuilder = new StringBuilder(); String ls =
	 * System.getProperty("line.separator");
	 * 
	 * while ((line = reader.readLine()) != null) { stringBuilder.append(line);
	 * stringBuilder.append(ls); }
	 * 
	 * reader.close(); return stringBuilder.toString(); }
	 */
	String author;
	String title;
	String[] pages;

	public LoreItem(String author, String title, String[] pages)
	{
		this.setMaxStackSize(1);
		this.author = author;
		this.title = title;
		this.pages = pages;
	}

	public static boolean validBookTagContents(NBTTagCompound p_77828_0_)
	{
		if (!ItemWritableBook.func_150930_a(p_77828_0_))
		{
			return false;
		}
		else if (!p_77828_0_.hasKey("title", 8))
		{
			return false;
		}
		else
		{
			String s = p_77828_0_.getString("title");
			return s != null && s.length() <= 16 ? p_77828_0_.hasKey("author", 8) : false;
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if (p_77653_1_.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = p_77653_1_.getTagCompound();
			String s = nbttagcompound.getString("title");

			if (!StringUtils.isNullOrEmpty(s))
			{
				return s;
			}
		}

		return super.getItemStackDisplayName(p_77653_1_);
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String s = nbttagcompound.getString("author");

			if (!StringUtils.isNullOrEmpty(s))
			{
				p_77624_3_.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] { s }));
			}
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p_77659_3_)
	{
		// if (!world.isRemote)
		// {
		p_77659_3_.displayGUIBook(stack);
		// }
		if (stack.getTagCompound() == null)
		{
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			NBTTagList bookPages = new NBTTagList();
			for (int i = 0; i < pages.length; i++)
			{
				NBTTagString page = new NBTTagString(pages[i]);
				bookPages.appendTag(page);
			}
			stack.setTagInfo("pages", bookPages);
			stack.setTagInfo("author", new NBTTagString(author));
			stack.setTagInfo("title", new NBTTagString(title));
		}
		return stack;
	}

	/**
	 * If this function returns true (or the item is damageable), the
	 * ItemStack's NBT tag will be sent to the client.
	 */
	@Override
	public boolean getShareTag()
	{
		return true;
	}
}
