Ext.define('Visitors.data.store.AppartmentStore', {
    extend: 'Ext.data.Store',
    id: 'appartmentStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'appartmentStore',
            proxy: {
                type: 'ajax',
                url: 'appartment/appartmentList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'companyId'
                },
                {
                    name: 'id'
                },
                {
                    name: 'name'
                }
            ]
        }, cfg)]);
    }
});
