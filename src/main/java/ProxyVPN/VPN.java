package ProxyVPN;

public class VPN {
        private DinamicProxy dinamicProxy; //=new DinamicProxy();
        private String adress;

        public VPN(String region){
            this.dinamicProxy=new DinamicProxy(region);
            this.adress=dinamicProxy.getAdress();
        }
        public void getAnotherAdress(String region){
            dinamicProxy.getProxyFromfreeproxylistnet(region);
            this.adress=dinamicProxy.getAdress();
        }
        public String getAdress(){
            return this.adress;
        }

}
