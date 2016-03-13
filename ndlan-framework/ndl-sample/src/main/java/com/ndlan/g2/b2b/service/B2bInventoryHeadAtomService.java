package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryHeadBean;

import java.util.List;

public interface B2bInventoryHeadAtomService {
    /**
     * �������ݶ���
     * @param b2bInventoryHeadBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bInventoryHead��
     * @param b2bInventoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * �������ݶ���
     * @param b2bInventoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bInventoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bInventoryHeadBean getB2bInventoryHeadBean(String invHeadId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bInventoryHeadBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String invHeadId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bInventoryHeadBean trimStringValue(B2bInventoryHeadBean b2bInventoryHead);

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean bean, Long startPos, Long num);

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean bean);

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
