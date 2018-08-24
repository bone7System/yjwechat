package com.yj.domain.commondity.service.impl;


import com.yj.common.exception.NHExpHandleUtils;
import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.domain.commondity.model.ErpCommodityPriceEntity;
import com.yj.domain.commondity.repository.ErpCommodityDetailRepository;
import com.yj.domain.commondity.repository.ErpCommodityPriceRepository;
import com.yj.domain.commondity.repository.ErpCommodityRepository;
import com.yj.domain.commondity.service.ErpCommodityService;
import com.yj.pojo.commidity.ErpCommodityPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ErpCommodityServiceImpl implements ErpCommodityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ErpCommodityRepository erpCommodityRepository;
    @Autowired
    private ErpCommodityDetailRepository erpCommodityDetailRepository;
    @Autowired
    private ErpCommodityPriceRepository erpCommodityPriceRepository;

    @Override
    public Integer insert(ErpCommodityPojo dto) throws Exception {
        try {
            ErpCommodityEntity cPO = new ErpCommodityEntity();
            cPO.setSpbm(dto.getSpbm());
            cPO.setSpmc(dto.getSpmc());
            cPO.setSpms(dto.getSpms());
            cPO.setSplxdm(dto.getSplxdm());
            cPO.setSppp(dto.getSppp());
            cPO.setMdid(dto.getMdid());
            cPO.setDelFlag(0);
            cPO=erpCommodityRepository.save(cPO);
            ErpCommodityDetailEntity cDetailPO = new ErpCommodityDetailEntity();
            cDetailPO.setGg(dto.getGg());
            cDetailPO.setDj(dto.getDj());
            cDetailPO.setDwdm(dto.getDwdm());
            cDetailPO.setSpid(cPO.getId());
            cDetailPO.setDelFlag(0);
            cDetailPO=erpCommodityDetailRepository.save(cDetailPO);
            ErpCommodityPriceEntity cPricelPO = new ErpCommodityPriceEntity();
            cPricelPO.setBzjg(dto.getBzjg());
            cPricelPO.setSpxqid(cDetailPO.getId());
            cPricelPO.setDelFlag(0);
            erpCommodityPriceRepository.save(cPricelPO);
            return 1;
        } catch (Exception e) {
            logger.error(e.getMessage());
            NHExpHandleUtils.throwesException(e);
        }
        return 1;
    }

    @Override
    public Integer update(ErpCommodityPojo dto) throws Exception {
        try {
            ErpCommodityEntity cPO = new ErpCommodityEntity();
            cPO.setId(dto.getSpid());
            cPO.setSpbm(dto.getSpbm());
            cPO.setSpmc(dto.getSpmc());
            cPO.setSpms(dto.getSpms());
            cPO.setSplxdm(dto.getSplxdm());
            cPO.setSppp(dto.getSppp());
            cPO.setMdid(dto.getMdid());
            cPO.setDelFlag(0);
            erpCommodityRepository.save(cPO);
            ErpCommodityDetailEntity cDetailPO = new ErpCommodityDetailEntity();
            cDetailPO.setId(dto.getSpxqid());
            cDetailPO.setGg(dto.getGg());
            cDetailPO.setDj(dto.getDj());
            cDetailPO.setDwdm(dto.getDwdm());
            cDetailPO.setSpid(dto.getSpid());
            cDetailPO.setDelFlag(0);
            erpCommodityDetailRepository.save(cDetailPO);
            ErpCommodityPriceEntity cPricelPO = new ErpCommodityPriceEntity();
            cPricelPO.setId(dto.getSpjgid());
            cPricelPO.setBzjg(dto.getBzjg());
            cPricelPO.setSpxqid(dto.getSpxqid());
            cPricelPO.setDelFlag(0);
            erpCommodityPriceRepository.save(cPricelPO);
            return 1;
        } catch (Exception e) {
            logger.error(e.getMessage());
            NHExpHandleUtils.throwesException(e);
        }
        return 1;
    }

    @Override
    public ErpCommodityPojo get(Integer pkid) {
        return erpCommodityRepository.findBySpid(pkid);
    }

    /**
     * 删除操作，其实是修改删除状态未1，表示已经删除
     * @param pkids
     * @return
     */
    @Override
    public Integer deleteMulti(Integer[] pkids) {
        erpCommodityPriceRepository.updateDelFlag(1,pkids);
        erpCommodityDetailRepository.updateDelFlag(1,pkids);
        erpCommodityRepository.updateDelFlag(1,pkids);
        return 1;
    }
}
