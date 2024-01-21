package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * DB�ڑ������擾����
 * 
 * @author ayaka
 *
 */
public class DBconfig {

	// �f�[�^�x�[�X�̐ڑ������擾���郁�\�b�h
	public Map<String, String>  getDBinfo() throws FileNotFoundException {

		// �v���p�e�B�t�@�C���̃t���p�X���w��
		String db_properties_file = "/eclipse-jee-oxygen-3a-win32-x86_64/workspace/CustomerManagement/DBconfig.properties";
		
		//��L�̃v���p�e�B�t�@�C����ǂݍ��ވׂɕK�v��Properties�N���X���C���X�^���X��
		Properties db_info = new Properties();
		//�t�@�C����Java���z�}�V���ɓǂݍ��ވׂ̓��o�̓X�g���[���N���X
		FileInputStream db_file_stream = new FileInputStream(db_properties_file);

		/**
		 * @param db_url �ڑ����
		 * @param db_user ���[�U�[��
		 * @param db_password �p�X���[�h
		 * @return DB�̐ڑ����
		 * @throws IOException
		 */
		try {
			// �v���p�e�B�t�@�C����ǂݍ���
			db_info.load(db_file_stream);
			
			//DBconfig.properties�̃L�[����l���擾����
			String db_url = db_info.getProperty("url");
			String db_user = db_info.getProperty("user");
			String db_pass = db_info.getProperty("password");

			// �擾�����f�[�^�x�[�X�̐ڑ�����Map�Ɋi�[����
			Map<String,String> getDBinfoForMap = new HashMap<>();

			getDBinfoForMap.put("url", db_url);
			getDBinfoForMap.put("user", db_user);
			getDBinfoForMap.put("password", db_pass);
			
			// DBconfig�N���X��
			// getDBinfo���\�b�h���Ăяo���ꂽ�ۂ�
			// �w�ڑ����A���[�U���A�p�X���[�h�x�̏���Ԃ�
			return getDBinfoForMap;
			
		} catch (IOException e) {
			System.out.println("�f�[�^�x�[�X�ݒ�t�@�C�����F���ł��܂���ł���");
			e.printStackTrace();
		}
		return null;
	}
}