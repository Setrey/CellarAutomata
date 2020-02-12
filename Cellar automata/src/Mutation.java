public class Mutation
	{
		public double	pMutationChangeStrategy=0.00;
		public double	pMutationChangePc=0.00;
		public double	pMutationHistory=0.00;
		public double	pMutationEpsilon=0.00;
		public double	parameterIncMutation=0.05;
		public int		parameterHisotryMutation=00;
		public double	parameterEpsilonMutation=0.00;
		
		public Mutation(double pMutationChangeStrategy,double pMutationChangePc,double pMutationHistory
				,double pMutationEpsilon,double parameterIncMutation,int parameterHisotryMutation
				,double parameterEpsilonMutation)
		{
			this.pMutationChangeStrategy=pMutationChangeStrategy;
			this.pMutationChangePc=pMutationChangePc;
			this.pMutationHistory=pMutationHistory;
			this.pMutationEpsilon=pMutationEpsilon;
			this.parameterIncMutation=parameterIncMutation;
			this.parameterHisotryMutation=parameterHisotryMutation;
			this.parameterEpsilonMutation=parameterEpsilonMutation;
		}
		public Mutation(Mutation mutation)
		{
			this.pMutationChangeStrategy=mutation.pMutationChangeStrategy;
			this.pMutationChangePc=mutation.pMutationChangePc;
			this.pMutationHistory=mutation.pMutationHistory;
			this.pMutationEpsilon=mutation.pMutationEpsilon;
			this.parameterIncMutation=mutation.parameterIncMutation;
			this.parameterHisotryMutation=mutation.parameterHisotryMutation;
			this.parameterEpsilonMutation=mutation.parameterEpsilonMutation;
		}
		public Mutation()
		{
			this.pMutationChangeStrategy=0;
			this.pMutationChangePc=0;
			this.pMutationHistory=0;
			this.pMutationEpsilon=0;
			this.parameterIncMutation=0;
			this.parameterHisotryMutation=0;
			this.parameterEpsilonMutation=0;
		}
	}