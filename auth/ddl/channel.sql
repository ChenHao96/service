DROP TABLE QPManagerPlatformDB.dbo.channelItem;
create QPManagerPlatformDB.dbo.channelItem(
  id          INT identity(1, 1) PRIMARY KEY,
  text    VARCHAR(16) NOT NULL,
  value    VARCHAR(50) UNIQUE NOT NULL,
  group    VARCHAR(255)
);

DROP TABLE QPManagerPlatformDB.dbo.channelItem;
create QPManagerPlatformDB.dbo.channelItemPermission(
  id INT identity(1, 1) PRIMARY KEY,
  user_id INT NOT NULL,
  channel_item_id INT NOT NULL
);

alter table QPManagerPlatformDB.dbo.channelItemPermission add constraint channelItemPermission_user_id_channel_item_id_unique_key unique (user_id, channel_item_id);