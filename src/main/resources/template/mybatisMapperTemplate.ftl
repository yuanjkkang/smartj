<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ${copyright} -->
<!-- ${author} on ${generateTime} -->
<mapper namespace="${pkg}.dao.${clzz}DAO">
    <resultMap id="BaseResultMap" type="${pkg}.model.${clzz}">
     <#list fields as field>
        <#if field.priKey == false><result column="${field.column}" jdbcType="${field.jdbcType}" property="${field.name}" /><#else><id column="${field.column}" jdbcType="${field.jdbcType}" property="${field.name}"/></#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
      <#list fields as field><#if field_index!=0>, ${field.column}<#else>${field.column}</#if></#list>
    </sql>

    <select id="queryById" parameterType="${keyFields[0].type}" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${table}
        WHERE ${keyFields[0].column} = <#if true>#</#if>{${keyFields[0].name}, jdbcType=${keyFields[0].jdbcType}}
    </select>

    <delete id="deleteById" parameterType="${keyFields[0].type}">
        DELETE FROM ${table}
        WHERE ${keyFields[0].column} = <#if true>#</#if>{${keyFields[0].name}, jdbcType=${keyFields[0].jdbcType}}
    </delete>

    <insert id="insert" useGeneratedKeys="true" parameterType="${pkg}.model.${clzz}" keyProperty="id" keyColumn="id">
        INSERT INTO ${table} (<#list insertFields as field><#if field_index==0>${field.column}<#else>, ${field.column}</#if></#list>)
        VALUES ( <#list insertFields as field><#if field_index==0><#if true>#</#if>{${field.name}, jdbcType=${field.jdbcType}}<#else>, <#if true>#</#if>{${field.name}, jdbcType=${field.jdbcType}}</#if></#list> )
    </insert>

    <update id="updateById" parameterType="${keyFields[0].type}">
        UPDATE ${table}
        SET <#list insertFields as field><#if field_index==0>${field.column} = <#if true>#</#if>{${field.name}, jdbcType=${field.jdbcType}}<#else>, ${field.column} = <#if true>#</#if>{${field.name}, jdbcType=${field.jdbcType}}</#if></#list>
        WHERE ${keyFields[0].column} = <#if true>#</#if>{${keyFields[0].name}, jdbcType=${keyFields[0].jdbcType}}
    </update>

</mapper>