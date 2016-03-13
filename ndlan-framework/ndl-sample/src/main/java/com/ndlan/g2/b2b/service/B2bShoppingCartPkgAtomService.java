package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartPkgBean;

import java.util.List;

public interface B2bShoppingCartPkgAtomService {
    /**
     * �������ݶ���
     * @param b2bShoppingCartPkgBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bShoppingCartPkgBean(B2bShoppingCartPkgBean b2bShoppingCartPkg);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bShoppingCartPkg��
     * @param b2bShoppingCartPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bShoppingCartPkgBean b2bShoppingCartPkg);

    /**
     * �������ݶ���
     * @param b2bShoppingCartPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bShoppingCartPkgBean b2bShoppingCartPkg);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bShoppingCartPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bShoppingCartPkgBean(B2bShoppingCartPkgBean b2bShoppingCartPkg);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bShoppingCartPkgBean getB2bShoppingCartPkgBean(String cartPkgId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bShoppingCartPkgBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String cartPkgId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bShoppingCartPkgBean trimStringValue(B2bShoppingCartPkgBean b2bShoppingCartPkg);

    public List<B2bShoppingCartPkgBean> queryB2bShoppingCartPkgBean
	(B2bShoppingCartPkgBean bean, Long startPos, Long num);

    public List<B2bShoppingCartPkgBean> queryB2bShoppingCartPkgBean
	(B2bShoppingCartPkgBean bean);

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
