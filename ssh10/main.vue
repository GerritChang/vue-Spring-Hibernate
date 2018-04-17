<template>
<div class="main">
  <Modal v-model="addUserModal" title="添加用户" @on-ok="addUser(addUserForm)">
    <Form ref="addUserForm" :model="addUserForm" :rules="checkNewUser" :label-width="80" style="width:90%;">
      <FormItem prop="username">
      <Input v-model="addUserForm.username" placeholder="请输入新的用户名"/>
      </FormItem>
      <FormItem prop="realname">
      <Input v-model="addUserForm.realname" placeholder="请输入真实姓名"/>
      </FormItem>
      <FormItem prop="age">
      <Input v-model="addUserForm.age" placeholder="请输入新的年龄"/>
      </FormItem>
      <FormItem prop="address">
      <Input v-model="addUserForm.address" placeholder="请输入新地址"/>
      </FormItem>
    </Form>
  </Modal>
  <Modal v-model="editUserModal" title="编辑用户" @on-ok="editUser(editUserForm)">
    <Form ref="editUserForm" :model="editUserForm" :rules="checkUser" :label-width="80" style="width:90%;">
      <FormItem prop="username">
      <Input v-model="editUserForm.username" placeholder="请输入新的用户名"/>
      </FormItem>
      <FormItem prop="realname">
      <Input v-model="editUserForm.realname" placeholder="请输入真实姓名"/>
      </FormItem>
      <FormItem prop="age">
      <Input v-model="editUserForm.age" placeholder="请输入新的年龄"/>
      </FormItem>
      <FormItem prop="address">
      <Input v-model="editUserForm.address" placeholder="请输入新地址"/>
      </FormItem>
    </Form>
  </Modal>
  <Modal v-model="confirmDeleteUser" ref="User" :model="User" width="300" @on-ok="delUser(User)">
    <p>确定删除该用户？此操作不可逆</p>
  </Modal>
  <Row :gutter="16">
    <Col span="12">
      <Form ref="formCustom01" :model="formCustom01" style="margin-left:20%" >
        <FormItem prop="username">
        <Input v-model="formCustom01.usernameKey" placeholder="请输入用户名模糊查询" style="margin-top:5px;" />
        <Button type="ghost" @click="findUserByUsernameKey(formCustom01)" style="">搜索</Button>
      </FormItem>
    </Form>
   </Col>
   <Col span="10" >
  <Form ref="formCustom02" :model="formCustom02" style="margin-right:3.5%" >
    <FormItem prop="realname">
    <Input v-model="formCustom02.realnameKey" placeholder="请输入真实姓名迷糊查询" style="margin-top:5px;" />
    <Button type="ghost" @click="findUserByRealnameKey(formCustom02)" style="">搜索</Button>
    </FormItem>
  </Form>
  </Col>
  <Col span="2">
  <Button type="ghost" @click="addUserModal = true" style="margin-top:5px;margin-right:10%">添加</Button>
  </Col>
  </Row>
  <Table border :columns="columns1" :data="newData" style="margin-left:10%;margin-right:10%"></Table>
</div>
</template>
<script>
import qs from 'qs';
export default {
  data() {
    return {
      User:'',
      confirmDeleteUser:false,
      addUserModal:false,
      editUserModal: false,
      formCustom01: {
        usernameKey: ''
      },
      formCustom02: {
        realnameKey: ''
      },
      addUserForm: {
        username: '',
        realname: '',
        age: '',
        address: ''
      },
      editUserForm: {
        userId:'',
        username: '',
        realname: '',
        age: '',
        address: ''
      },
      checkNewUser: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        realname: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
      },
      checkUser: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        realname: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
      },
      newData: [],
      msg: '',
      columns1: [
        {
          title: 'UserId',
          key: 'userId'
        },
        {
          title: 'Username',
          key: 'username'
        },
        {
          title: 'Realname',
          key: 'realname'
        },
        {
          title: 'Age',
          key: 'age'
        },
        {
          title: 'Address',
          key: 'address'
        },
        {
          title: 'Action',
          key: 'action',
          width: 200,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'Button',
                {
                  props: {
                    type: 'primary'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.showModal(params);
                    }
                  }
                },
                '编辑'
              ),
              h(
                'Button',
                {
                  props: {
                    type: 'error'
                  },
                  on: {
                    click: () => {
                      this.showdelUserModal(params);
                    }
                  }
                },
                '删除'
              )
            ]);
          }
        }
      ]
    };
  },
  methods: {
    addUser(User) {
      this.$axios
        .post(
          'http://localhost:8081/ssh10/user/addUser',
          qs.stringify(User, {
            username: User.username,
            realname: User.realname,
            age: User.age,
            address: User.address
          })
        )
        .then(res => {
          this.$Message.info(res.data);
          this.$axios
            .post('http://localhost:8081/ssh10/user/getUserList')
            .then(res => {
              this.newData = res.data;
            });
        })
        .catch(error => {
          console.log(error);
        });
    },
    showModal(User){
      this.editUserForm.userId = User.row.userId;
      this.editUserForm.username = User.row.username;
      this.editUserForm.realname = User.row.realname;
      this.editUserForm.age = User.row.age;
      this.editUserForm.address = User.row.address;
      this.editUserModal = true;
    },
    showdelUserModal(User){
      this.User = User;
      console.log(this.User);
      this.confirmDeleteUser = true;
    },
    editUser(User) {
      console.log(User.userId);
      this.$axios.post("http://localhost:8081/ssh10/user/updateUser",qs.stringify(User, {
            userId:User.userId,
            username: User.username,
            realname: User.realname,
            age: User.age,
            address: User.address
          }))
          .then(res => {
          this.$Message.info(res.data);
          this.$axios
            .post('http://localhost:8081/ssh10/user/getUserList')
            .then(res => {
              this.newData = res.data;
            });
        })
        .catch(error => {
          console.log(error);
        });
    },
    delUser: function(params) {
      this.$axios
        .get('http://localhost:8081/ssh10/user/delUser/' + params.row.userId)
        .then(res => {
          this.$Message.info(res.data);
          this.$axios
            .post('http://localhost:8081/ssh10/user/getUserList')
            .then(res => {
              this.newData = res.data;
            });
        })
        .catch(error => {
          console.log(error);
        });
    },
    findUserByUsernameKey(params) {
      this.$axios
        .get(
          'http://localhost:8081/ssh10/user/findUserByUsernameKey/' +
            params.usernameKey
        )
        .then(res => {
          if (res.data.length !== 0) {
            this.$Message.info('您查询的数据如下');
            this.newData = res.data;
          } else if (res.data.length === 0) {
            this.$Message.error('对不起，找不到您搜索的数据');
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    findUserByRealnameKey(params) {
      // console.log(params.realnameKey)
      this.$axios
        .get(
          'http://localhost:8081/ssh10/user/findUserByRealnameKey/' +
            params.realnameKey
        )
        .then(res => {
          if (res.data.length !== 0) {
            this.$Message.info('您查询的数据如下');
            this.newData = res.data;
          } else if (res.data.length === 0) {
            this.$Message.error('对不起，找不到您搜索的数据');
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  created() {
    this.$axios
      .post('http://localhost:8081/ssh10/user/getUserList')
      .then(res => {
        this.newData = res.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
<style scoped>

</style>
