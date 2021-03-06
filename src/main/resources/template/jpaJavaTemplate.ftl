/*
 * ${copyright}
 */
package ${pkg};  

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ${desc}
 * @author ${author}
 * @date ${generateTime}
 */  
 
@Entity
@Table(name = "${table}")
public class ${clzz} {
<#list keyFields as field>
    <#if field.comment != "">/** ${field.comment} */</#if>
    @Id(name="${field.column}")
    private ${field.type} ${field.name};
</#list>
<#list fields as field>
    <#if field.priKey == true><#continue></#if>
    <#if field.comment != "">/** ${field.comment} */</#if>
    @Column(name="${field.column}")
    private ${field.type} ${field.name};
</#list>
  
  <#list fields as field>  
    public ${field.type} get${field.name?cap_first}(){  
      return ${field.name};  
    }  
    
    public void set${field.name?cap_first}(${field.type} ${field.name}){  
      this.${field.name} = ${field.name};  
    } 
     
  </#list>

    @Override
    public String toString() {
        return "${clzz}[" +
            <#list fields as field>
                <#if field_index!=0> + ",${field.name} = " + ${field.name}<#else>"${field.name} = " + ${field.name}</#if>
            </#list>
        + "]";
    }
}  