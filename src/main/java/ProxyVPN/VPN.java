package ProxyVPN;

public class VPN {
        private DinamicProxy dinamicProxy=new DinamicProxy();
        private String adress;


        public VPN(){
            dinamicProxy.getProxyFromfreeproxylistnet();
            this.adress=dinamicProxy.getAdress();
        }
        public void getAnotherAdress(){
            dinamicProxy.getProxyFromfreeproxylistnet();
            this.adress=dinamicProxy.getAdress();
        }
        public String getAdress(){
            return this.adress;
        }

}
