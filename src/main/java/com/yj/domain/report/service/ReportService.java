package com.yj.domain.report.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.report.KcDto;
import org.springframework.data.domain.Pageable;

public interface ReportService {
    ReSult searchStore(KcDto kcDto, Pageable pageable, UserDetail user);
}
