package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderStatusHisBean;

import java.util.List;

public interface B2bOrderStatusHisAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderStatusHisBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderStatusHisBean(B2bOrderStatusHisBean b2bOrderStatusHis);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderStatusHis��
     * @param b2bOrderStatusHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderStatusHisBean b2bOrderStatusHis);

    /**
     * �������ݶ���
     * @param b2bOrderStatusHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderStatusHisBean b2bOrderStatusHis);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderStatusHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderStatusHisBean(B2bOrderStatusHisBean b2bOrderStatusHis);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderStatusHisBean getB2bOrderStatusHisBean(String orderStatusHisId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderStatusHisBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String orderStatusHisId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderStatusHisBean trimStringValue(B2bOrderStatusHisBean b2bOrderStatusHis);

    public List<B2bOrderStatusHisBean> queryB2bOrderStatusHisBean
	(B2bOrderStatusHisBean bean, Long startPos, Long num);

    public List<B2bOrderStatusHisBean> queryB2bOrderStatusHisBean
	(B2bOrderStatusHisBean bean);

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
