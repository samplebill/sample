package org.mifosplatform.commands.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_portfolio_command_source")
public class CommandSource extends AbstractPersistable<Long> {

    @Column(name = "action_name", nullable = true, length = 100)
    private String actionName;
    
    @Column(name = "entity_name", nullable = true, length = 100)
    private String entityName;
    
    @SuppressWarnings("unused")
    @Column(name = "office_id")
    private Long officeId;

    @SuppressWarnings("unused")
    @Column(name = "group_id")
    private Long groupId;
    
    @SuppressWarnings("unused")
    @Column(name = "client_id")
    private Long clientId;
    
    @SuppressWarnings("unused")
    @Column(name = "loan_id")
    private Long loanId;
    
    @SuppressWarnings("unused")
    @Column(name = "api_get_url", length = 100)
    private String resourceGetUrl;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "command_as_json", length = 1000)
    private String commandAsJson;

    @SuppressWarnings("unused")
    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private AppUser maker;

    @SuppressWarnings("unused")
    @Column(name = "made_on_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date madeOnDate;

    @SuppressWarnings("unused")
    @ManyToOne
    @JoinColumn(name = "checker_id", nullable = true)
    private AppUser checker;

    @SuppressWarnings("unused")
    @Column(name = "checked_on_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkedOnDate;

    @SuppressWarnings("unused")
    @Column(name = "processing_result_enum", nullable = false)
    private Integer processingResult;

    public static CommandSource fullEntryFrom(final CommandWrapper wrapper, final JsonCommand command, final AppUser maker) {
        return new CommandSource(wrapper.actionName(), wrapper.entityName(), wrapper.getHref(), command.entityId(), command.json(), maker, DateTime.now());
    }

    protected CommandSource() {
        //
    }

    private CommandSource(
            final String actionName, final String entityName, final String href,
            final Long resourceId,
            final String commandSerializedAsJson, final AppUser maker,
            final DateTime madeOnDateTime) {
        this.actionName = actionName;
        this.entityName = entityName;
        this.resourceGetUrl = href;
        this.resourceId = resourceId;
        this.commandAsJson = commandSerializedAsJson;
        this.maker = maker;
        this.madeOnDate = madeOnDateTime.toDate();
        this.processingResult = CommandProcessingResultType.PROCESSED.getValue();
    }

    public void markAsChecked(final AppUser checker, final DateTime checkedOnDate) {
        this.checker = checker;
        this.checkedOnDate = checkedOnDate.toDate();
        this.processingResult = CommandProcessingResultType.PROCESSED.getValue();
    }

    public void updateResourceId(final Long resourceId) {
        this.resourceId = resourceId;
    }

    public void updateJsonTo(final String json) {
        this.commandAsJson = json;
    }

    public Long resourceId() {
        return this.resourceId;
    }

    public boolean hasJson() {
        return StringUtils.isNotBlank(this.commandAsJson);
    }

    public String json() {
        return this.commandAsJson;
    }

    public String getActionName() {
        return this.actionName;
    }
    
    public String getEntityName() {
        return this.entityName;
    }

    public String getPermissionCode() {
        return this.actionName + "_" + this.entityName;
    }

    public Long getResourceId() {
        return this.resourceId;
    }

    public void markAsAwaitingApproval() {
        this.processingResult = CommandProcessingResultType.AWAITING_APPROVAL.getValue();
    }

    public void updateForAudit(final Long officeId, final Long groupId, final Long clientId, final Long loanId) {
        this.officeId = officeId;
        this.groupId = groupId;
        this.clientId = clientId;
        this.loanId = loanId;
    }
}