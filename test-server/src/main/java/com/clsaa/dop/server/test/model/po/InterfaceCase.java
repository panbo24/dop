package com.clsaa.dop.server.test.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xihao
 * @version 1.0
 * @since 04/03/2019
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interface_case", schema = "db_dop_test",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"case_name", "application_id"})},
        indexes = {@Index(columnList = "case_name,application_id", unique = true),
                @Index(columnList = "application_id,case_name", unique = false)})
public class InterfaceCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_name")
    private String caseName;

    @Column(name = "case_desc")
    private String caseDesc;

    @Column(name = "pre_condition")
    private String preCondition;

    @Column(name = "application_id")
    private Long applicationId;

    private String steps;

    @Column(name = "predicate_result")
    private String predicateResult;

    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id", referencedColumnName = "id")
    private List<InterfaceStage> stages;

    // ----------- common property ---------
    private LocalDateTime ctime;

    private LocalDateTime mtime;

    private Long cuser;

    private Long muser;

    @Column(name = "is_deleted")
    private boolean deleted;
}