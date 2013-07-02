Ext.define('Visitors.data.store.FlatStore', {
    extend: 'Ext.data.Store',
    id: 'flatStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: false,
            storeId: 'flatStore',
            proxy: {
                type: 'ajax',
                url: 'flat/flatList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'appartmentId'
                },
                {
                    name: 'appartmentName'  
                },
                {
                    name: 'id'
                },
                {
                    name: 'flatNo'
                }
            ]
        }, cfg)]);
    }
});
