package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryStorageBean;

import java.util.List;

public interface B2bInventoryStorageAtomService {
    /**
     * �������ݶ���
     * @param b2bInventoryStorageBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bInventoryStorage��
     * @param b2bInventoryStorage ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * �������ݶ���
     * @param b2bInventoryStorage ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bInventoryStorage ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bInventoryStorageBean getB2bInventoryStorageBean(String invStorageId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bInventoryStorageBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String invStorageId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bInventoryStorageBean trimStringValue(B2bInventoryStorageBean b2bInventoryStorage);

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean bean, Long startPos, Long num);

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean bean);

    /**
     * ����sql������ɾ��
     * @param whereSql where�ؼ��ֺ����ɾ�����������磺id = ?
     * @param params �����еĲ�����������Ҫ��"?"��������һ��
     * @return �ɹ�ɾ���ļ�¼��
     */
    public int deleteByWhereSql(String whereSql, Object[] params);

     /**
    * ��������
    * @param sql �������
    * @param args ����
    * @return �ɹ����µļ�¼��
    */
    public int update(String sql, Object... args);

}
