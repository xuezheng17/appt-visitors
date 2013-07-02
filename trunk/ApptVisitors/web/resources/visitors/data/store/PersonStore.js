Ext.define('Visitors.data.store.PersonStore',{
    extend: 'Ext.data.Store',
    id: 'personStore',
    

    constructor: function(cfg){
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'personStore',
            proxy:{
                type: 'ajax',
                url: 'user/personList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields:[
                {
                    name: 'id'
                },
                {
                    name: 'firstName'
                },
                {
                    name: 'middleInitial'
                },
                {
                    name: 'lastName'
                },
                {
                    name: 'phone1'
                },
                {
                    name: 'email'
                },
                {
                    name: 'empCode'
                },
                {
                    name: 'appartmentHiddenName'
                },
                {
                    name: 'appartmentId'
                },
                {
                    name: 'flatHiddenName'
                },
                {
                    name: 'flatId'
                },
                {
                    name: 'picture'
                },
                {
                    name: 'personType'
                },
                {
                    name: 'personTypeName'
                },
                {
                    name: 'onlineStatusId'
                },
                {
                    name: 'password'
                }
            ]
        }, cfg)])
    }
})

