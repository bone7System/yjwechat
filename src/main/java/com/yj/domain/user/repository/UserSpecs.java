package com.yj.domain.user.repository;

import com.yj.domain.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecs {
    public static Specification<User> setQuery(final String searchName) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                String key = "%" + searchName + "%";

                Path<String> path1 = root.get("username");
                Path<String> path2 = root.get("mobile");
                Path<String> path3 = root.get("email");

                Predicate predicate = builder.or(builder.like(path1, key), builder.like(path2, key),builder.like(path3, key));

                if (predicate == null) {
                    query.where();
                } else {
                    query.where(predicate);
                }
                return predicate;
            }
        };
    }
}
