package work;
import java.util.Scanner;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class My_base64{
	public static void main(String[] args) {
		//下面是需要base64的文件的地址路径
		String base64Str = imageToBase64Str("D:\\gxq\\my_project\\Java\\base64demo\\src\\work\\1.jpg");
		System.out.println(base64Str);
		
		
		//Scanner cin = new Scanner(System.in);
		//String base64Str = cin.next();
		//下面是把字符串base64Str进行解密，生成到后面的地址路径
		boolean b = base64StrToImage(base64Str, "D:\\gxq\\my_project\\Java\\base64demo\\src\\work\\2.jpg");
		System.out.println(b);
	}
	/**
	 * base64编码字符串转换为图片
	 * 
	 * @param imgStr base64编码字符串
	 * @param path   图片路径
	 * @return
	 */
	public static boolean base64StrToImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		Decoder decoder = Base64.getDecoder();
		try {
			// 解密
			byte[] b = decoder.decode(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			// 文件夹不存在则自动创建
			File tempFile = new File(path);
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(tempFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 图片转base64字符串
	 * 
	 * @param imgFile 图片路径
	 * @return
	 */
	public static String imageToBase64Str(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		Encoder encoder = Base64.getEncoder();
		// BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeToString(data);
	}
}
