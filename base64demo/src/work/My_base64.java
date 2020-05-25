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
		//��������Ҫbase64���ļ��ĵ�ַ·��
		String base64Str = imageToBase64Str("D:\\gxq\\my_project\\Java\\base64demo\\src\\work\\1.jpg");
		System.out.println(base64Str);
		
		
		//Scanner cin = new Scanner(System.in);
		//String base64Str = cin.next();
		//�����ǰ��ַ���base64Str���н��ܣ����ɵ�����ĵ�ַ·��
		boolean b = base64StrToImage(base64Str, "D:\\gxq\\my_project\\Java\\base64demo\\src\\work\\2.jpg");
		System.out.println(b);
	}
	/**
	 * base64�����ַ���ת��ΪͼƬ
	 * 
	 * @param imgStr base64�����ַ���
	 * @param path   ͼƬ·��
	 * @return
	 */
	public static boolean base64StrToImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		Decoder decoder = Base64.getDecoder();
		try {
			// ����
			byte[] b = decoder.decode(imgStr);
			// ��������
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			// �ļ��в��������Զ�����
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
	 * ͼƬתbase64�ַ���
	 * 
	 * @param imgFile ͼƬ·��
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
		// ����
		Encoder encoder = Base64.getEncoder();
		// BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeToString(data);
	}
}
