package com.mytest.model;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.mytest.config.BaseConfig;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {
	
	public static DataSource getDataSource() {
		PropKit.use("config.txt");
		C3p0Plugin c3p0Plugin = BaseConfig.createC3p0Plugin();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.mytest.model.base.system";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/mytest/model/base/system";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.mytest.model.system";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/../..";
		
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		//不同的数据库反射生成 meta 信息有一些差别，所以为了可靠性，以及性能就没有在 getter setter 方法上生成这这些 meta 信息，这些信息生成在了 _DataDictionary.txt 文件之中，需要先打开一下这个功能：
		gernerator.setGenerateDataDictionary(true);
		// 添加不需要生成的表名
		gernerator.addExcludedTable("admin_log");
		gernerator.addExcludedTable("admin_user");
		gernerator.addExcludedTable("admin_menu");
		gernerator.addExcludedTable("user_menu");
		gernerator.addExcludedTable("files");
		gernerator.addExcludedTable("news");
		gernerator.addExcludedTable("categories");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("admin_");
		// 生成
		gernerator.generate();
	}
}




