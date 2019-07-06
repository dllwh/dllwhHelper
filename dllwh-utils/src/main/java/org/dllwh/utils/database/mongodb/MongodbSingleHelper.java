package org.dllwh.utils.database.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import com.google.common.collect.Sets;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: mongo 单机 工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午1:47:44
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class MongodbSingleHelper {
	private volatile MongoClient	mongoClient;
	private MongoDatabase			mongoDatabase;
	/** 服务器地址 */
	private String					host;
	/** 服务器端口 */
	private int						port;
	/** 是否开启验证 */
	private boolean					auth	= false;
	/** 用户名 */
	private String					userName;
	/** 数据库名称 */
	private String					databaseName;
	/** 密码 */
	private String					password;

	public MongodbSingleHelper(String host, int port, String databaseName) {
		this.host = host;
		this.port = port;
		this.databaseName = databaseName;
		this.auth = false;
		getMongoDataBase();
	}

	public MongodbSingleHelper(String host, int port, String userName, String databaseName, String password) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.databaseName = databaseName;
		this.password = password;
		this.auth = true;
		getMongoDataBase();
	}

	/**
	 * @方法描述: 获取Mongo客户端
	 * @return
	 */
	private synchronized MongoClient getMongoClient(String dbName) {
		if (mongoClient == null) {
			if (!auth) {
				// 连接到 mongodb 服务
				mongoClient = new MongoClient(host, port);
			} else {
				// 认证信息（三个参数分别为：用户名、数据库名称、密码）
				MongoCredential credential = MongoCredential.createCredential(userName, dbName,
						password.toCharArray());
				List<MongoCredential> credentials = new ArrayList<MongoCredential>();
				credentials.add(credential);
				ServerAddress serverAddress = new ServerAddress(host, port);

				// 可以通过builder做各种详细配置
				MongoClientOptions.Builder builder = MongoClientOptions.builder();
				MongoClientOptions options = builder.build();

				mongoClient = new MongoClient(serverAddress, credential, options);
			}
		}
		return mongoClient;
	}

	/**
	 * @方法描述 : 获取到指定db（若不存在，则mongo会创建该db)
	 * @param databaseName
	 * @return
	 */
	private MongoDatabase getMongoDataBase() {
		if (mongoDatabase == null) {
			if (mongoClient == null) {
				getMongoClient(databaseName);
			}
			mongoDatabase = mongoClient.getDatabase(databaseName);
		}
		return mongoDatabase;
	}

	/**
	 * @方法描述: 获取所有db名称列表（mongodb未开启auth认证下可用）
	 * @return
	 * @throws Exception
	 */
	public Set<String> getAllDatabaseNames() throws Exception {
		Set<String> resultSet = Sets.newHashSet();
		MongoCursor<String> mongoCursor = null;
		try {
			MongoClient mongoClient = getMongoClient(databaseName);
			MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
			mongoCursor = databaseNames.iterator();
			while (mongoCursor.hasNext()) {
				resultSet.add(mongoCursor.next());
			}
		} finally {
			// 需要手动去释放它
			if (mongoCursor != null) {
				mongoCursor.close();
			}
		}
		return resultSet;
	}

	/**
	 * @方法描述 : 创建集合
	 */
	public void createCollection(String collectionName) {
		mongoDatabase.createCollection(collectionName);
	}

	/**
	 * @方法描述 : 删除集合
	 * @param databaseName
	 * @param CollectionName
	 */
	public void dropCollection(String collectionName) {
		mongoDatabase.getCollection(collectionName).drop();
	}

	/**
	 * @方法描述: 获取所有集合
	 * @return
	 */
	public Set<String> getAllCollectionNames() {
		Set<String> resultSet = Sets.newHashSet();
		for (String name : mongoDatabase.listCollectionNames()) {
			resultSet.add(name);
		}
		return resultSet;
	}

	public MongoCollection<Document> getCollection(String collectionName) {
		if (StringUtils.isBlank(collectionName)) {
			return null;
		}
		return mongoDatabase.getCollection(collectionName);
	}
}