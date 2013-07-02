Ext.define('Visitors.data.store.PersonTypeStore', {
    extend: 'Ext.data.Store',
    id: 'employeeTypeStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'personTypeStore',
            proxy: {
                type: 'ajax',
                url: 'util/personTypeList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'value'
                }
            ]
        }, cfg)]);
    }
});
