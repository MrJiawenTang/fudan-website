package com.cloud.kpi.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {

	final static String DB_URL = "jdbc:mysql://120.26.58.188:3306/fudanWebSite?characterEncoding=utf8";
	final static String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	final static String DB_USER_NAME = "root";
	final static String DB_PASSWORD = "密码";
	/** 项目名称 */
	static final String CORE_SRC = "cqc-service-core";
	/** 源代码路径 */
	static final String CODE_SRC = "\\src\\main\\java";
	/** 源代码路径 */
	static final String RESOURCES_SRC = "\\src\\main\\resources";
	/** 开发人员 */
	final static String AUTHOR = "eric.wang";
	/** 表格前缀 */
	final static String[] TABLE_PREFIX = new String[] { "t_" };
	/** 是否覆盖已有文件 */
	final static boolean IS_FILE_ORRIDE = false;

	public static void main(String[] args) {

		// 获取生成源码路径
		String rootPath = (new File(System.getProperty("user.dir"))).getParent();

		String codePath = rootPath + File.separator + "cqc-service-core";

		String base = "com.cloud.cqc.service";

		Map<String, String[]> modules = new HashMap<>();
		// 管理模块
		// modules.put("admin", new String[] { "t_user", "t_role", "t_resource" });
		// 内容管理模块
		modules.put("meeting", new String[] { "t_meeting", "t_meeting_participants"});

		for (Entry<String, String[]> entry : modules.entrySet()) {
			execute(codePath, base, entry.getKey(), entry.getValue());
		}

	}

	private static void execute(String root, String base, String module, String[] tables) {

		String outPut = root + CODE_SRC;

		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outPut);
		gc.setFileOverride(IS_FILE_ORRIDE);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setOpen(false);// 生成后不打开
		gc.setAuthor(AUTHOR);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName(DB_DRIVER_NAME);
		dsc.setUrl(DB_URL);
		dsc.setUsername(DB_USER_NAME);
		dsc.setPassword(DB_PASSWORD);

		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				if ("int(10) unsigned".equals(fieldType)) {
					return DbColumnType.LONG;
				} else if ("tinyint(1)".equals(fieldType)) {
					return DbColumnType.BOOLEAN;
				}
				return super.processTypeConvert(fieldType);
			}
		});

		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix(TABLE_PREFIX);// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(tables);
		// 自定义实体，公共字段
		strategy.setSuperEntityColumns(
				new String[] { "id", "deleted", "create_by", "create_time", "last_update_by", "last_update_time" });
		// 自定义实体父类
		strategy.setSuperEntityClass("com.cloud.cqc.framework.persistence.entity.BasicEntity");
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.cloud.cqc.framework.persistence.service.IBaseService");
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass("com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(base);
		pc.setModuleName(module);
		mpg.setPackageInfo(pc);

		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("VO", joinPackage(pc.getParent(), "vo"));
				this.setMap(map);
			}
		};

		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

		focList.add(new FileOutConfig("/templates/vo.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return joinPath(outPut, joinPackage(pc.getParent(), "vo")) + File.separator + tableInfo.getEntityName()
						+ "VO.java";
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		TemplateConfig tc = new TemplateConfig();
		// 不生成controller
		tc.setController(null);
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();
	}

	/**
	 * <p>
	 * 连接父子包名
	 * </p>
	 *
	 * @param parent
	 *            父包名
	 * @param subPackage
	 *            子包名
	 * @return 连接后的包名
	 */
	private static String joinPackage(String parent, String subPackage) {
		if (StringUtils.isEmpty(parent)) {
			return subPackage;
		}
		return parent + "." + subPackage;
	}

	/**
	 * <p>
	 * 连接路径字符串
	 * </p>
	 *
	 * @param parentDir
	 *            路径常量字符串
	 * @param packageName
	 *            包名
	 * @return 连接后的路径
	 */
	private static String joinPath(String parentDir, String packageName) {
		if (StringUtils.isEmpty(parentDir)) {
			parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
		}
		if (!StringUtils.endsWith(parentDir, File.separator)) {
			parentDir += File.separator;
		}
		packageName = packageName.replaceAll("\\.", "\\" + File.separator);
		return parentDir + packageName;
	}

}
